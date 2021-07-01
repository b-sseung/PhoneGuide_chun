package org.techtown.guide.typing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;

import java.util.Random;

public class Typing_short extends AppCompatActivity {

    Toolbar toolbar;

    TextView question;
    EditText editText;
    String question_word;
    String editText_word;

    String[] question_test = new String[]{};
    String[] editText_test = new String[]{};
    SpannableStringBuilder spanBuilder;

    int randomNum;
    String quizText;

    FrameLayout touchLayout1;
    LinearLayout touchLayout2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typing_short);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("단어 입력 연습하기");
        setSupportActionBar(toolbar);

        touchLayout1 = findViewById(R.id.touchLayout);
        touchLayout2 = findViewById(R.id.touchLayout2);

        if (AppConstants.TYPING_S_ifValue){
            touchLayout1.setVisibility(View.VISIBLE);
        }

        question = findViewById(R.id.question);
        editText = findViewById(R.id.editText);

        change_quest();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                editText_word = s.toString();
                editText_test = editText_word.split("");

                spanBuilder = new SpannableStringBuilder(question_word);

                if (s.length() <= question.length()) {
                    Log.d("1818", "s : " + s.length());
                    Log.d("1818", "q : " + question.length());

                    for (int i = 0; i <= s.length(); i++) {

                        if (editText_test[i].equals(question_test[i])) {

                        } else {
                            Log.d("1818", "다름");
                            if ( i != 0){
                                spanBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), i - 1, i, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                            }
                        }
                    }
                } else if (s.length() > question.length()){
                    for (int i = 0; i <= question.length(); i++) {

                        if (editText_test[i].equals(question_test[i])) {

                        } else {
                            if (i != 0) {
                                spanBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), i - 1, i, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                            }
                        }
                    }
                }

                question.setText(spanBuilder);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //지우기 아까워서...
//                if (question_test.length < editText_test.length) {
//                    for (int i = 0; i < question_test.length; i++) {
//                        if (question_test[i].equals(editText_test[i])) {
//
//                        } else {
//                            spanBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), i - 1, i, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//                        }
//                    }
//                    if (question_test.length != editText_test.length) {
//                        spanBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), question_test.length - 1, editText_test.length - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//                    }
//                }

                if (editText.getText().toString().equals(question.getText().toString())){
                    change_quest();
                    if (AppConstants.typing_s_Level != AppConstants.LEVEL_CLEAR){
                        AppConstants.typing_s_Level = AppConstants.LEVEL_CLEAR;
                    }
                }
            }

        });

        Button backButton = findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
                intent.putExtra("toastNumber", 2);
                startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADPAGE);
            }
        });

        touchLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchLayout1.setVisibility(View.INVISIBLE);
                touchLayout2.setVisibility(View.VISIBLE);
            }
        });
        touchLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchLayout2.setVisibility(View.INVISIBLE);
                AppConstants.TYPING_S_ifValue = false;
            }
        });
    }

    protected void change_quest(){
        Random random = new Random();
        randomNum = random.nextInt(AppConstants.typing_shorts.length);
        quizText = AppConstants.typing_shorts[randomNum];
        question.setText(quizText);
        question_word = question.getText().toString();
        question_test = question_word.split("");
        editText.setText("");
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
        intent.putExtra("toastNumber", 7);
        startActivityForResult(intent, AppConstants.REQUEST_CODE_BACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstants.REQUEST_CODE_KEYPADPAGE){
            finish();
        } else if (requestCode == AppConstants.REQUEST_CODE_BACK){
            if (resultCode == RESULT_CANCELED){
                finish();
            }
        }
    }

}
