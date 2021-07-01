package org.techtown.guide.keypad_jaum;

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
import org.techtown.guide.main.MainActivity;

import java.util.Random;

public class KeypadMethodQuiz extends Fragment {

    ViewGroup rootView;

    EditText editText;
    TextView textView;

    int number = 0;
    boolean nextString = false;

    ViewGroup container3;

    String quizText;
    int randomNum;

    FrameLayout touchLayout;
    LinearLayout touchLayout2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.keypad_method_quiz, container, false);

        container3 = container;

        editText = rootView.findViewById(R.id.editText);
        textView = rootView.findViewById(R.id.text);

        quizSetting();
        Log.d("1818", "quiz number : " + number);

        touchLayout = rootView.findViewById(R.id.touchLayout);
        touchLayout2 = rootView.findViewById(R.id.touchLayout2);

        if (AppConstants.KeypadQuiz_ifValue){
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
                AppConstants.KeypadQuiz_ifValue = false;
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
                        editText.setText("");
                        number = 1;
                    } else if (number == 1){
                        quizSetting();
                        editText.setText("");
                        number = 2;
                    } else if (number == 2){
                        KeypadMethod.number = 0;

                        if ( AppConstants.Level[3] != AppConstants.LEVEL_CLEAR){
                            AppConstants.Level[3] = AppConstants.LEVEL_CLEAR;
                        }

                        InputMethodManager inputMethodManager =
                                (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                        Intent intent = new Intent(getActivity().getApplicationContext(), ToastActivity.class);
                        intent.putExtra("toastNumber", 5);
                        startActivityForResult(intent,AppConstants.REQUEST_CODE_KEYPADQUIZ2);

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
                KeypadMethod.number = 0;
                KeypadMethod2.number = 0;
                number = 0;
                InputMethodManager inputMethodManager =
                        (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                getFragmentManager().beginTransaction().replace(R.id.keylayout, new KeypadMethod()).commit();
                getFragmentManager().beginTransaction().replace(R.id.keylayout2, new KeypadMethod2()).commit();
                getFragmentManager().beginTransaction().replace(R.id.keylayout3, new KeypadMethodQuiz()).commit();
                getActivity().findViewById(R.id.keylayout).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.keylayout2).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.keylayout3).setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }

    public void quizSetting(){
        Random random = new Random();
        randomNum = random.nextInt(AppConstants.jaums.length);
        quizText = AppConstants.jaums[randomNum];
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
