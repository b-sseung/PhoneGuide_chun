package org.techtown.guide.keypad_etd;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;
import org.techtown.guide.keypad_jaum.Keypad_jaum_Ex_1;
import org.techtown.guide.main.MainActivity;

import java.util.ArrayList;

public class KeypadPage3 extends AppCompatActivity {

    static int number = 0;
    static Toolbar toolbar;

    LinearLayout layout1;
    LinearLayout layout2;
    FrameLayout layout3;
    FrameLayout exLayout;
    FrameLayout exLayout2;
    FrameLayout fragLayout;

    TextView text;
    ImageView image;
    ImageView exImage;
    ImageView keypadimage;
    EditText editText;

    TextView touch;
    TextView touch2;
    TextView touch3;
    TextView touch4;
    TextView[] touchs;
    FrameLayout tFrame1;
    FrameLayout tFrame2;
    FrameLayout tFrame3;
    FrameLayout tFrame4;
    String[] editString = new String[]{};
    ArrayList<String> editArray = new ArrayList<String>();


    Button replayButton;

    int[] sebuImages;
    String[] sebuTexts;
    int[] keypadImages;
    int clickNum = 0;

    Animation scale;

    int c = 0;
    int line = 1;
    TextView skipText;

    Handler handler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keypad_page3);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("문자 이외의 키패드");
        setSupportActionBar(toolbar);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        exLayout = findViewById(R.id.exLayout);
        exLayout2 = findViewById(R.id.exLayout2);
        fragLayout = findViewById(R.id.fragLayout);

        getSupportFragmentManager().beginTransaction().add(R.id.fragLayout, new KeypadMethodQuiz3()).commit();

        keypadimage = findViewById(R.id.imageKeypad);
        image = findViewById(R.id.sebuImage);
        exImage = findViewById(R.id.exImage);
        text = findViewById(R.id.sebuText);

        editText = findViewById(R.id.editText);
        editText.setShowSoftInputOnFocus(false);

        sebuImages = new int[]{R.drawable.keypad_delete_sebu, R.drawable.keypad_empty_sebu,
                                R.drawable.keypad_enter_sebu, R.drawable.keypad_symbol_sebu};
        sebuTexts = new String[]{"글자를 지우는 버튼입니다.", "띄어쓰기 버튼입니다.",
                "줄을 바꾸거나 \n" + "입력을 완료한다는 버튼입니다.", "기본적인 특수문자 버튼입니다."};
        keypadImages = new int[]{R.drawable.keypad_delete, R.drawable.keypad_empty, R.drawable.keypad_enter, R.drawable.keypad_symbol};

        touch = findViewById(R.id.touch);
        touch2 = findViewById(R.id.touch2);
        touch3 = findViewById(R.id.touch3);
        touch4 = findViewById(R.id.touch4);
        touchs = new TextView[]{touch, touch2, touch3, touch4};
        tFrame1 = findViewById(R.id.tFrame1);
        tFrame2 = findViewById(R.id.tFrame2);
        tFrame3 = findViewById(R.id.tFrame3);
        tFrame4 = findViewById(R.id.tFrame4);

        scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_keypad2);

        if (AppConstants.KeypadPage3_ifValue){
            Intent intent = new Intent(this, Keypad_jaum_Ex_1.class);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_MAIN);
            AppConstants.KeypadPage3_ifValue = false;
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                clickMethod(number);
            }
        });

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                clickMethod(number);
            }
        });

        Button sebuButton = findViewById(R.id.sebuButton);
        sebuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickNum == 0){
                    text.setVisibility(View.VISIBLE);
                    image.setVisibility(View.INVISIBLE);
                    clickNum = 1;
                } else if (clickNum == 1){
                    text.setVisibility(View.INVISIBLE);
                    image.setVisibility(View.VISIBLE);
                    clickNum = 0;
                }
            }
        });

        replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_setting(number);
            }
        });

        exLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exLayout.setVisibility(View.INVISIBLE);
            }
        });
        exLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exLayout2.setVisibility(View.INVISIBLE);
            }
        });

        tFrame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 5){
                    editText.setText("");
                    if (editArray.size() != 0){
                        editArray.remove(editArray.size() - 1);
                    }
                    for (int b = 0; b < editArray.size(); b++){
                        editText.append(editArray.get(b));
                    }
                }
            }
        });
        tFrame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 6){
                    editText.setText("");
                    editArray.add(editArray.size() - 1, " ");
                    for (int b = 0; b < editArray.size(); b++){
                        editText.append(editArray.get(b));
                    }
                    editText.setSelection(editText.length() - 1);
                }
            }
        });
        tFrame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 7){
                    editText.setText("");
                    line++;
                    editArray.add("\n" + line + "번째 줄");
                    for (int b = 0; b < editArray.size(); b++){
                        editText.append(editArray.get(b));
                    }
                    editText.setSelection(editText.length());
                }
            }
        });
        tFrame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 8){
                    String[] symbols = new String[]{".", ",", "?", "!"};

                    editText.setText(symbols[c % 4]);
                    editText.setSelection(editText.length());
                    c++;
                }
            }
        });

        skipText = findViewById(R.id.skipText);
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keypadimage.setImageResource(R.drawable.keypad_etd);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, new KeypadMethodQuiz3()).commit();
                fragLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    protected void clickMethod(int position){

        clickNum = 0;

        if (position == -1) {
            Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
            intent.putExtra("toastNumber", 2);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADPAGE);

            number = 0;
        } else if (position == 0){

            layout1.setVisibility(View.VISIBLE);
            isVisibilityView(layout2);
            toolbar.setTitle("문자 이외의 키패드");

            keypadimage.setImageResource(R.drawable.keypad_etd);

        } else if(position == 1){

            toolbar.setTitle("기능 생각해보기");

            if (AppConstants.KeypadPage3_ifValue2){
                exLayout.setVisibility(View.VISIBLE);
                AppConstants.KeypadPage3_ifValue2 = false;
            }

            layout2.setVisibility(View.VISIBLE);
            isVisibilityView(layout1);
            layout2_clickMethod(position);
        } else if (position == 2 || position == 3 || position == 4){
            isVisibilityView(layout3);

            if (layout2.getVisibility() != View.VISIBLE){
                layout2.setVisibility(View.VISIBLE);
            }

            layout2_clickMethod(position);

            stopAnim();

        } else if (position == 5) {
            toolbar.setTitle("기능 이해하기");

            layout3.setVisibility(View.VISIBLE);
            isVisibilityView(layout2);

            layout3_clickMethod(position);
            stopAnim();
            edit_setting(position);


            if (AppConstants.KeypadPage3_ifValue3){
                exLayout2.setVisibility(View.VISIBLE);
                AppConstants.KeypadPage3_ifValue3 = false;
            }

            touch.startAnimation(scale);

        } else if (position == 6) {

            layout3_clickMethod(position);
            stopAnim();
            edit_setting(position);

            touch2.startAnimation(scale);

        } else if (position == 7) {

            layout3_clickMethod(position);
            stopAnim();
            edit_setting(position);

            touch3.startAnimation(scale);

        } else if (position == 8) {

            layout3_clickMethod(position);
            stopAnim();
            edit_setting(position);

            touch4.startAnimation(scale);

        } else if (position == 9){

            stopAnim();

            number = 0;
            keypadimage.setImageResource(R.drawable.keypad_etd);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragLayout, new KeypadMethodQuiz3()).commit();
            fragLayout.setVisibility(View.VISIBLE);

        }
    }

    public void isVisibilityView(View view){

        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }
    }

    protected void layout2_clickMethod(int position){
        int num = position - 1;

        text.setText(sebuTexts[num]);
        image.setImageResource(sebuImages[num]);
        keypadimage.setImageResource(keypadImages[num]);
        isVisibilityView(text);
        image.setVisibility(View.VISIBLE);
    }

    protected void layout3_clickMethod(int position){

        int num = position - 5;

        exImage.setImageResource(sebuImages[num]);
        keypadimage.setImageResource(keypadImages[num]);
    }

    protected void edit_setting(int position){

        editArray.clear();

        if (position == 5){
            //글자 지우는 것
            editText.setText("지워주세요.");
            editText.setSelection(editText.length());
        } else if (position == 6){
            //띄어쓰기
            editText.setText("간격");
            editText.setSelection(editText.length() - 1);
        } else if (position == 7){
            //엔터
            line = 1;
            editText.setText(line + "번째 줄");
            editText.setSelection(editText.length());
        } else if (position == 8){
            //기호
            editText.setText("");
            editText.setSelection(editText.length());
        }

        editString = editText.getText().toString().split("");
        for (int a = 0; a < editString.length; a++){
            editArray.add(editString[a]);
        }

        c = 0;

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

    protected void stopAnim(){
        for (int i = 0; i < touchs.length; i++){
            touchs[i].clearAnimation();
        }

    }

}
