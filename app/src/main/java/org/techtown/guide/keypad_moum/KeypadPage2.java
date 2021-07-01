package org.techtown.guide.keypad_moum;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;
import org.techtown.guide.keypad_jaum.KeypadPage;
import org.techtown.guide.keypad_jaum.Keypad_jaum_Ex_1;

public class KeypadPage2 extends AppCompatActivity {

    Toolbar toolbar;

    static int number = 0;
    int image = 0;

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout2_1;
    LinearLayout layout2_2;
    FrameLayout fragment_layout;
    FrameLayout fragment_layout2;

    ImageView moum1;
    ImageView moum2;
    ImageView moum3;

    KeypadMethod3 next;
    KeypadMethodQuiz2 nextQuiz;

    TextView skipText;

    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keypad_page2);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("모음 입력 방법");
        setSupportActionBar(toolbar);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout2_1 = findViewById(R.id.layout2_1);
        layout2_2 = findViewById(R.id.layout2_2);
        fragment_layout = findViewById(R.id.fragment_layout);
        fragment_layout2 = findViewById(R.id.fragment_layout2);

        moum1 = findViewById(R.id.moum1);
        moum2 = findViewById(R.id.moum2);
        moum3 = findViewById(R.id.moum3);

        if (AppConstants.KeypadPage2_ifValue){
            Intent intent = new Intent(this, Keypad_jaum_Ex_1.class);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_MAIN);
            AppConstants.KeypadPage2_ifValue = false;
        }

        next = new KeypadMethod3();
        nextQuiz = new KeypadMethodQuiz2();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, next).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout2, nextQuiz).commit();

        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                Log.d("1818", "number : " + number);
                clickMethod(number);
            }
        });

        Button next = findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number++;
                clickMethod(number);
            }
        });

        skipText = findViewById(R.id.skipText);
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.fragment_layout2).setVisibility(View.VISIBLE);
            }
        });
    }

    public void clickMethod(int position){

        if (position == -1){
            //메세지박스 -> 시작으로 돌아갈지 자음입력으로 돌아갈지 정하기 (취소하면 position=0 화면 보이게 설정하기
            Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
            intent.putExtra("toastNumber", 3);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADPAGE2);

            number = 0;
        } else if (position == 0) {
            //layout1만 보이게
            //1->0이면 layout2비활성화, layout1활성화
            isVisibilityView(layout2);
            layout1.setVisibility(View.VISIBLE);

        } else if (position == 1){
            //layout2랑 layout2_1만 보이게
            //0->1이면 layout1비활성화 layout2랑 layout2_1활성화
            //2->1이면 layout2_2비활성화 layout2_1활성화
            isVisibilityView(layout1);
            layout2.setVisibility(View.VISIBLE);
            layout2_1.setVisibility(View.VISIBLE);

            if (image == 1){
                moum1.setImageResource(R.drawable.moum_1);
                moum2.setImageResource(R.drawable.moum_2_1);
                moum3.setImageResource(R.drawable.moum_3_1);
                image = 0;
            }

            isVisibilityView(layout2_2);

        } else if (position == 2){
            //layout2랑 layout2_2만 보이게
            //1->2이면 layout2_1 비활성화 layout2_2활성화
            //3->2이면
            isVisibilityView(layout2_1);
            layout2_2.setVisibility(View.VISIBLE);
            image = 1;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moum1.setImageResource(R.drawable.moum_1_2);
                    moum2.setImageResource(R.drawable.moum_2_2);
                    moum3.setImageResource(R.drawable.moum_3_2);
                }
            }, 100);

        } else if (position == 3) {
            number = 2;
            fragment_layout.setVisibility(View.VISIBLE);
        }
    }

    public void isVisibilityView(View view){

        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }

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
        if (requestCode == AppConstants.REQUEST_CODE_KEYPADPAGE2){
            if (resultCode == RESULT_OK){
                finish();
            } else if (resultCode == RESULT_CANCELED){
                Intent intent = new Intent(getApplicationContext(), KeypadPage.class);
                intent.putExtra("return", false);
                startActivity(intent);

                finish();
            }
        } else if (requestCode == AppConstants.REQUEST_CODE_KEYPADQUIZ2){
            AppConstants.KeypadPage2_ifValue = false;
        } else if (requestCode == AppConstants.REQUEST_CODE_BACK){
            if (resultCode == RESULT_CANCELED){
                finish();
                KeypadMethod3.number = 0;
            }
        }

    }
}
