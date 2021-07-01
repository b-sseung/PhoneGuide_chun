package org.techtown.guide.keypad_moum;

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
import androidx.fragment.app.Fragment;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;
import org.techtown.guide.keypad_jaum.KeypadMethod;
import org.techtown.guide.keypad_jaum.KeypadMethod2;
import org.techtown.guide.keypad_jaum.KeypadMethodQuiz;
import org.techtown.guide.main.MainActivity;

import java.util.Random;

public class KeypadMethodQuiz2 extends Fragment {

    ViewGroup rootView;

    EditText editText;
    TextView textView;

    int number = 0;
    boolean nextString = false;

    ViewGroup keypadpage2;

    String quizText;
    int randomNum;

    FrameLayout touchLayout;
    LinearLayout touchLayout2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.keypad_method_quiz2, container, false);

        keypadpage2 = container;

        editText = rootView.findViewById(R.id.editText);
        textView = rootView.findViewById(R.id.text);

        quizSetting();

        touchLayout = rootView.findViewById(R.id.touchLayout);
        touchLayout2 = rootView.findViewById(R.id.touchLayout2);

        if (AppConstants.KeypadQuiz2_ifValue){
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
                AppConstants.KeypadQuiz2_ifValue = false;
            }
        });

        Log.d("1818", "quiz2 number : " + number);

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
                        editText.setText("");
                        number = 1;
                    } else if (number == 1){
                        quizSetting();
                        editText.setText("");
                        number = 2;
                    } else if (number == 2){

                        if ( AppConstants.Level[4] != AppConstants.LEVEL_CLEAR){
                            AppConstants.Level[4] = AppConstants.LEVEL_CLEAR;
                        }

                        number = 0;

                        InputMethodManager inputMethodManager =
                                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                        Intent intent = new Intent(getContext(), ToastActivity.class);
                        intent.putExtra("toastNumber", 5);
                        startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADQUIZ2);
                    }
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ToastActivity.class);
                    intent.putExtra("toastNumber", 4);
                    startActivityForResult(intent,AppConstants.REQUEST_CODE_KEYPADQUIZ);

                    editText.setText("");
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

                getActivity().findViewById(R.id.layout2).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.layout1).setVisibility(View.VISIBLE);
                KeypadPage2.number = 0;
                KeypadMethod3.number = 0;

                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new KeypadMethod3()).commit();
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout2, new KeypadMethodQuiz2()).commit();
                getActivity().findViewById(R.id.fragment_layout).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.fragment_layout2).setVisibility(View.INVISIBLE);
            }
        });

        return rootView;
    }

    public void quizSetting(){
        Random random = new Random();
        randomNum = random.nextInt(AppConstants.moums.length);
        quizText = AppConstants.moums[randomNum];
        textView.setText("'" + quizText + "'");
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
