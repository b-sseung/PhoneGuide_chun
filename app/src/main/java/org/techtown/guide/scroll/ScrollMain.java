package org.techtown.guide.scroll;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;
import org.techtown.guide.main.MainActivity;

import java.util.ArrayList;

public class ScrollMain extends AppCompatActivity {

    Toolbar toolbar;
    private AdView mAdView;

    FrameLayout layout1;
    FrameLayout layout2;
    FrameLayout ex_layout1;
    FrameLayout ex_layout2;
    FrameLayout ex_layout3;
        TextView ex_text;
        String[] ex_texts;
    FrameLayout ex_layout4;
        LinearLayout bar_updown;
        LinearLayout bar_leftright;
    FrameLayout ex_layout5;

    TextView menu1;
    TextView menu2;
    TextView menu3;
    TextView menu4;
    String textSet;

    TextView ver_text1;
    TextView ver_text2;
    TextView hor_text1;
    TextView hor_text2;

    TextView scroll_text;
    ScrollView scroll_ver1;
    ScrollView scroll_ver2;
    HorizontalScrollView scroll_hor1;
    HorizontalScrollView scroll_hor2;
    View[] scrolls;

    TranslateAnimation up;
    TranslateAnimation down;
    TranslateAnimation left;
    TranslateAnimation right;

    TextView circle_up;
    TextView circle_down;
    TextView circle_left;
    TextView circle_right;
    TextView[] circles;

    int number;

    TextView change1;
    TextView change2;
    TextView change3;
    TextView change4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_page);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("스크롤 배우기");
        setSupportActionBar(toolbar);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);

        menu1 = findViewById(R.id.text1);
        menu2 = findViewById(R.id.text2);
        menu3 = findViewById(R.id.text3);
        menu4 = findViewById(R.id.text4);

        ver_text1 = findViewById(R.id.ver1_text);
        ver_text2 = findViewById(R.id.ver2_text);
        hor_text1 = findViewById(R.id.hor1_text);
        hor_text2 = findViewById(R.id.hor2_text);

        ex_layout1 = findViewById(R.id.ex_layout1);
        ex_layout2 = findViewById(R.id.ex_layout2);
        ex_layout3 = findViewById(R.id.ex_layout3);
            ex_text = findViewById(R.id.ex_text);
            ex_texts = new String[]{"위로 밀어보세요.", "아래로 밀어보세요.", "왼쪽으로 밀어보세요.", "오른쪽으로 밀어보세요."};
        ex_layout4 = findViewById(R.id.ex_layout4);
            bar_leftright = findViewById(R.id.bar_leftright);
            bar_updown = findViewById(R.id.bar_updown);
        ex_layout5 = findViewById(R.id.ex_layout5);

        scroll_text = findViewById(R.id.scroll_text);
        scroll_ver1 = findViewById(R.id.scroll_ver1);
        scroll_ver2 = findViewById(R.id.scroll_ver2);
        scroll_hor1 = findViewById(R.id.scroll_hor1);
        scroll_hor2 = findViewById(R.id.scroll_hor2);

        circle_up = findViewById(R.id.circle_up);
        circle_down = findViewById(R.id.circle_down);
        circle_left = findViewById(R.id.circle_left);
        circle_right = findViewById(R.id.circle_right);
        circles = new TextView[]{circle_down, circle_up, circle_right, circle_left};

        up = new TranslateAnimation(0, 0, 0, -800);
        up.setDuration(2000);
        up.setFillAfter(false);
        up.setRepeatCount(-1);
        up.setStartOffset(500);

        down = new TranslateAnimation(0, 0, 0, 800);
        down.setDuration(2000);
        down.setFillAfter(false);
        down.setRepeatCount(-1);
        down.setStartOffset(500);

        left = new TranslateAnimation(0, -800, 0, 0);
        left.setDuration(2000);
        left.setFillAfter(false);
        left.setRepeatCount(-1);
        left.setStartOffset(500);

        right = new TranslateAnimation(0, 800, 0, 0);
        right.setDuration(2000);
        right.setFillAfter(false);
        right.setRepeatCount(-1);
        right.setStartOffset(500);

        scrolls = new View[]{scroll_ver1, scroll_ver2, scroll_hor1, scroll_hor2};

        change1 = findViewById(R.id.change1);
        change2 = findViewById(R.id.change2);
        change3 = findViewById(R.id.change3);
        change4 = findViewById(R.id.change4);

        if (AppConstants.SCROLL_ifValue){
            ex_layout1.setVisibility(View.VISIBLE);
            change1.setTextColor(Color.parseColor("#FFFFFF"));
            change2.setTextColor(Color.parseColor("#FFFFFF"));
            change3.setTextColor(Color.parseColor("#FFFFFF"));
            change4.setTextColor(Color.parseColor("#FFFFFF"));
        }

        ex_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout1.setVisibility(View.INVISIBLE);
                ex_layout2.setVisibility(View.VISIBLE);
            }
        });
        ex_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout2.setVisibility(View.INVISIBLE);
                change1.setTextColor(Color.parseColor("#000000"));
                change2.setTextColor(Color.parseColor("#000000"));
                change3.setTextColor(Color.parseColor("#000000"));
                change4.setTextColor(Color.parseColor("#88000000"));
                AppConstants.SCROLL_ifValue = false;
            }
        });

        ex_layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout3.setVisibility(View.INVISIBLE);
                circles[number].clearAnimation();

                if (number == 0 | number == 1){
                    if (AppConstants.SCROLL_ifValue2){
                        ex_layout4.setVisibility(View.VISIBLE);
                        bar_updown.setVisibility(View.VISIBLE);
                        AppConstants.SCROLL_ifValue2 = false;
                    }
                } else if (number == 2 | number == 3){
                    if (AppConstants.SCROLL_ifValue3){
                        ex_layout4.setVisibility(View.VISIBLE);
                        bar_leftright.setVisibility(View.VISIBLE);
                        AppConstants.SCROLL_ifValue3 = false;
                    }
                }
            }
        });
        ex_layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout4.setVisibility(View.INVISIBLE);
                ex_layout5.setVisibility(View.VISIBLE);
                if (number == 0 | number == 1){
                    bar_updown.setVisibility(View.INVISIBLE);
                } else if (number == 2 | number == 3){
                    bar_leftright.setVisibility(View.INVISIBLE);
                }
            }
        });
        ex_layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout5.setVisibility(View.INVISIBLE);
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("밑으로 이동하기");
                setLayout("화면을 위로 밀어서", 0);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("위로 이동하기");
                setLayout("화면을 아래로 밀어서", 1);

            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("오른쪽으로 이동하기");
                setLayout("화면을 왼쪽으로 밀어서", 2);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("왼쪽으로 이동하기");
                setLayout("화면을 오른쪽으로 밀어서", 3);
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
                intent.putExtra("toastNumber", 2);
                startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADPAGE);
            }
        });

        Button replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle_anim(number);
            }
        });

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                circles[number].clearAnimation();
            }
        });

        ver_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu1.setBackgroundColor(Color.parseColor("#FFD8D8"));
                check_clear(number);
            }
        });
        ver_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu2.setBackgroundColor(Color.parseColor("#FFD8D8"));
                check_clear(number);
            }
        });
        hor_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu3.setBackgroundColor(Color.parseColor("#FFD8D8"));
                check_clear(number);
            }
        });
        hor_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu4.setBackgroundColor(Color.parseColor("#FFD8D8"));
                check_clear(number);
            }
        });
    }



    protected void setLayout(String text, int position){
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.VISIBLE);

        number = position;

        scroll_text.setText(text);

        for (int i = 0; i < scrolls.length; i++){
            scrolls[i].setVisibility(View.INVISIBLE);
        }

        if (position == 0) {
            scroll_ver1.post(new Runnable() {
                @Override
                public void run() {
                    scroll_ver1.fullScroll(View.FOCUS_UP);
                }
            });
        } else if (position == 1) {
            scroll_ver2.post(new Runnable() {
                @Override
                public void run() {
                    scroll_ver2.fullScroll(View.FOCUS_DOWN);
                }
            });
        } else if (position == 2) {
            scroll_hor1.post(new Runnable() {
                @Override
                public void run() {
                    scroll_hor1.fullScroll(View.FOCUS_LEFT);
                }
            });
        } else if (position == 3) {
            scroll_hor2.post(new Runnable() {
                @Override
                public void run() {
                    scroll_hor2.fullScroll(View.FOCUS_RIGHT);
                }
            });
        }

        ex_layout3.setVisibility(View.VISIBLE);
        ex_text.setText(ex_texts[position]);
        circle_anim(position);

        scrolls[position].setVisibility(View.VISIBLE);


    }

    protected void circle_anim(int num){
        if (num == 0) {
            circle_down.startAnimation(up);
        } else if (num == 1) {
            circle_up.startAnimation(down);
        } else if (num == 2) {
            circle_right.startAnimation(left);
        } else if (num == 3) {
            circle_left.startAnimation(right);
        }
    }

    protected void check_clear(int num){
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        circles[num].clearAnimation();

        AppConstants.clearScroll[num] = 1;

        if (AppConstants.clearScroll[0] == 1){
            if (AppConstants.clearScroll[1] == 1){
                if (AppConstants.clearScroll[2] == 1){
                    if (AppConstants.clearScroll[3] == 1){

                        if ( AppConstants.Level[1] != AppConstants.LEVEL_CLEAR){
                            AppConstants.Level[1] = AppConstants.LEVEL_CLEAR;
                        }

                        Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
                        intent.putExtra("toastNumber", 6);
                        startActivityForResult(intent, AppConstants.REQUEST_CODE_SCROLL);
                    }
                }
            }
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

        if (requestCode == AppConstants.REQUEST_CODE_KEYPADPAGE){
            finish();
        } else if (requestCode == AppConstants.REQUEST_CODE_SCROLL){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(intent);
        } else if (requestCode == AppConstants.REQUEST_CODE_BACK){
            if (resultCode == RESULT_CANCELED){
                finish();
            }
        }
    }
}
