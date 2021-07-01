package org.techtown.guide.keypad_jaum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;

public class KeypadPage extends AppCompatActivity {

    static Toolbar toolbar;

    KeypadMethod keypad;
    KeypadMethod2 keypad2;
    KeypadMethodQuiz quiz;

    FragmentManager manager;

    TextView skipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keypad_page);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("키패드 이용 방법");
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();
        keypad = new KeypadMethod();
        keypad2 = new KeypadMethod2();
        quiz = new KeypadMethodQuiz();

        manager.beginTransaction().replace(R.id.keylayout, keypad).commit();
        manager.beginTransaction().replace(R.id.keylayout2, keypad2).commit();
        manager.beginTransaction().replace(R.id.keylayout3, quiz).commit();

        if (AppConstants.KeypadPage_ifValue){
            Intent intent = new Intent(this, Keypad_jaum_Ex_1.class);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_MAIN);
            AppConstants.KeypadPage_ifValue = false;
        }

        Button backbutton = findViewById(R.id.backButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
                intent.putExtra("toastNumber", 2);
                startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADPAGE);

            }
        });

        Button nextbutton = findViewById(R.id.nextButton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.keylayout).setVisibility(View.VISIBLE);
                toolbar.setTitle("자음 입력 방법");
            }
        });

        skipText = findViewById(R.id.skipText);
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.keylayout3).setVisibility(View.VISIBLE);
            }
        });

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
                KeypadMethod.number = 0;
                KeypadMethod2.number = 0;

                finish();
            }
        }
    }
}
