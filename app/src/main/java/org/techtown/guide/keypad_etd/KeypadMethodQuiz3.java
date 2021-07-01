package org.techtown.guide.keypad_etd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;
import org.techtown.guide.keypad_moum.KeypadMethod3;
import org.techtown.guide.keypad_moum.KeypadMethodQuiz2;
import org.techtown.guide.keypad_moum.KeypadPage2;
import org.techtown.guide.main.MainActivity;

import java.util.Random;

public class KeypadMethodQuiz3 extends Fragment {

    ViewGroup rootView;

    EditText editText;
    TextView textView;

    int number = 0;
    boolean nextString = false;

    String quizText;
    int randomNum;

    FrameLayout touchLayout;
    LinearLayout touchLayout2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.keypad_quiz3, container, false);

        editText = rootView.findViewById(R.id.editText);
        textView = rootView.findViewById(R.id.text);

        Log.d("1818", "quiz3 number : " + number);

        quizSetting();

        touchLayout = rootView.findViewById(R.id.touchLayout);
        touchLayout2 = rootView.findViewById(R.id.touchLayout2);

        if (AppConstants.KeypadQuiz3_ifValue){
            touchLayout.setVisibility(View.VISIBLE);
        }

        touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchLayout.setVisibility(View.INVISIBLE);
                touchLayout2.setVisibility(View.VISIBLE);
            }
        });

        touchLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchLayout2.setVisibility(View.INVISIBLE);
                AppConstants.KeypadQuiz3_ifValue = false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (number == 0){
                    if (editText.getText().toString().equals(quizText)){
                        nextString = true;
                    } else {
                        nextString = false;
                    }
                } else if (number == 1){
                    if (editText.getText().toString().equals(quizText)){
                        nextString = true;
                    } else {
                        nextString = false;
                    }
                } else if (number == 2){
                    if (editText.getText().toString().equals(quizText)){
                        nextString = true;
                    } else {
                        nextString = false;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button nextButton = rootView.findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextString){
                    if (number == 0){
                        quizSetting();
                        number = 1;
                    } else if (number == 1){
                        quizSetting();
                        number = 2;
                    } else if (number == 2){

                        if ( AppConstants.Level[2] != AppConstants.LEVEL_CLEAR){
                            AppConstants.Level[2] = AppConstants.LEVEL_CLEAR;
                        }

                        InputMethodManager inputMethodManager =
                                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                        number = 0;

                        Intent intent = new Intent(getActivity().getApplicationContext(), ToastActivity.class);
                        intent.putExtra("toastNumber", 5);
                        startActivityForResult(intent,AppConstants.REQUEST_CODE_KEYPADQUIZ2);

                    }
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ToastActivity.class);
                    intent.putExtra("toastNumber", 4);
                    startActivityForResult(intent,AppConstants.REQUEST_CODE_KEYPADQUIZ);

                    if (randomNum == 4){
                        editText.setText("지워주세요");
                        editText.setSelection(editText.length());
                    } else {
                        editText.setText("");
                    }
                }
            }
        });

        Button backButton = rootView.findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = 0;

                InputMethodManager inputMethodManager =
                        (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                getActivity().findViewById(R.id.fragLayout).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.layout3).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.layout2).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.layout1).setVisibility(View.VISIBLE);

            }
        });

        return rootView;
    }

    public void quizSetting(){
        Random random = new Random();
        randomNum = random.nextInt(AppConstants.symbols.length);
        quizText = AppConstants.symbols_answer[randomNum];
        textView.setText(AppConstants.symbols[randomNum]);

        if (randomNum == 4){
            editText.setText("지워주세요");
            editText.setSelection(editText.length());
        } else {
            editText.setText("");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstants.REQUEST_CODE_KEYPADQUIZ2){
            Intent intent = new Intent(getContext(), MainActivity.class);
            getActivity().finish();
            startActivity(intent);
        }
    }
}
