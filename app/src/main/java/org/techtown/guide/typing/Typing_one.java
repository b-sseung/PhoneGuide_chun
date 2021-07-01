package org.techtown.guide.typing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

public class Typing_one extends AppCompatActivity {

    Toolbar toolbar;

    int number = 0;

    LinearLayout layout1;
    FrameLayout layout2;
    FrameLayout layout3;
    LinearLayout layout4;
    FrameLayout t1;
    FrameLayout t2;
    FrameLayout t3;
    FrameLayout t4;
    FrameLayout t5;
    FrameLayout t6;
    FrameLayout[] tlayouts;

    TextView text;
    EditText editText;

    ImageView r1_image1;
    ImageView r1_image2;
    ImageView r1_image3;
    ImageView r1_image4;
    TextView r1_text1;
    TextView r1_text2;
    TextView r1_text3;
    ImageView[] image1s;
    TextView[] text1s;

    ImageView r2_image1;
    ImageView r2_image2;
    ImageView r2_image3;
    ImageView r2_image4;
    ImageView r2_image5;
    ImageView r2_image6;
    TextView r2_text1;
    TextView r2_text2;
    TextView r2_text3;
    TextView r2_text4;
    TextView r2_text5;
    ImageView[] image2s;
    TextView[] text2s;


    TextView touch1;
    TextView touch2;
    TextView touch3;
    TextView touch4;
    TextView touch5;
    TextView touch6;
    TextView[] touchs;

    //입력방법보여줄 때
    Animation scale1;
    Animation scale2;
    animListener listener = new animListener();
    animListener2 listener2 = new animListener2();
    int animNum = 0;
    boolean stopsign = true;

    int animNext = 0;

    //따라입력할 때
    Animation scale3;
    int touchNum = 0;

    Handler handler = new Handler();

    FrameLayout ex_layout1;
    FrameLayout ex_layout2;
    FrameLayout ex_layout3;

    TextView skipText;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typing_one);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("글자 입력 방법");
        setSupportActionBar(toolbar);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);

        text = findViewById(R.id.text);
        editText = findViewById(R.id.editText);
        editText.setShowSoftInputOnFocus(false);

        r1_image1 = findViewById(R.id.root1_image1);
        r1_image2 = findViewById(R.id.root1_image2);
        r1_image3 = findViewById(R.id.root1_image3);
        r1_image4 = findViewById(R.id.root1_image4);
        image1s = new ImageView[]{r1_image1, r1_image2, r1_image3, r1_image4};

        r1_text1 = findViewById(R.id.root1_text1);
        r1_text2 = findViewById(R.id.root1_text2);
        r1_text3 = findViewById(R.id.root1_text3);
        text1s = new TextView[]{r1_text1, r1_text2, r1_text3};

        r2_image1 = findViewById(R.id.root2_image1);
        r2_image2 = findViewById(R.id.root2_image2);
        r2_image3 = findViewById(R.id.root2_image3);
        r2_image4 = findViewById(R.id.root2_image4);
        r2_image5 = findViewById(R.id.root2_image5);
        r2_image6 = findViewById(R.id.root2_image6);
        image2s = new ImageView[]{r2_image1, r2_image2, r2_image3, r2_image4, r2_image5, r2_image6};

        r2_text1 = findViewById(R.id.root2_text1);
        r2_text2 = findViewById(R.id.root2_text2);
        r2_text3 = findViewById(R.id.root2_text3);
        r2_text4 = findViewById(R.id.root2_text4);
        r2_text5 = findViewById(R.id.root2_text5);
        text2s = new TextView[]{r2_text1, r2_text2, r2_text3, r2_text4, r2_text5};

        touch1 = findViewById(R.id.touch); //ㄴ
        touch2 = findViewById(R.id.touch2); //.
        touch3 = findViewById(R.id.touch3); //ㅣ
        touch4 = findViewById(R.id.touch4); //ㄱ
        touch5 = findViewById(R.id.touch5); //ㅡ
        touch6 = findViewById(R.id.touch6); //ㄷ
        touchs = new TextView[]{touch1, touch2, touch3, touch4, touch5, touch6};

        t1 = findViewById(R.id.touchlayout);
        t2 = findViewById(R.id.touchlayout2);
        t3 = findViewById(R.id.touchlayout3);
        t4 = findViewById(R.id.touchlayout4);
        t5 = findViewById(R.id.touchlayout5);
        t6 = findViewById(R.id.touchlayout6);

        scale1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_keypad);
        scale2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_keypad);
        scale1.setAnimationListener(listener);
        scale2.setAnimationListener(listener2);

        scale3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_keypad2);

        ex_layout1 = findViewById(R.id.ex_Layout1);
        ex_layout2 = findViewById(R.id.ex_Layout2);
        ex_layout3 = findViewById(R.id.ex_Layout3);

        skipText = findViewById(R.id.skipText);

        if (AppConstants.TYPING_O_ifValue){
            ex_layout1.setVisibility(View.VISIBLE);
        }

        ex_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout1.setVisibility(View.INVISIBLE);
                AppConstants.TYPING_O_ifValue = false;

            }
        });
        ex_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout2.setVisibility(View.INVISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (number == 2){
                            touch1.startAnimation(scale1);
                        }
                    }
                }, 3000);
                AppConstants.TYPING_O_ifValue1 = false;

            }
        });
        ex_layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout3.setVisibility(View.INVISIBLE);
                AppConstants.TYPING_O_ifValue2 = false;
            }
        });

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

        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Typing_one_Quiz.class);
                startActivity(intent);
                finish();
            }
        });

        Button replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animNext == 1){
                    editText.setText("");
                    stopsign = true;

                    animNext = 0;
                    if (number == 2 | number == 3){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch1.startAnimation(scale1);
                            }
                        }, 500);

                        resetViews(image1s, text1s);
                        touch1.setTextColor(Color.parseColor("#FF0000"));
                        touch2.setTextColor(Color.parseColor("#FF0000"));
                        touch3.setTextColor(Color.parseColor("#FF0000"));
                    } else if (number == 4 | number == 5){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch4.startAnimation(scale1);
                            }
                        }, 500);

                        resetViews(image2s, text2s);
                        touch4.setTextColor(Color.parseColor("#FF0000"));
                        touch5.setTextColor(Color.parseColor("#FF0000"));
                        touch6.setTextColor(Color.parseColor("#FF0000"));
                    }
                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchNum == 1){
                    touch1.clearAnimation();
                    touch2.startAnimation(scale3);
                    editText.setText("ㄴ");
                    editText.setSelection(editText.length());
                    touchNum++;
                }
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchNum == 2){
                    touch2.clearAnimation();
                    touch3.startAnimation(scale3);
                    editText.setText("ㄴ·");
                    editText.setSelection(editText.length());
                    touchNum++;
                }
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchNum == 3){
                    editText.setText("너");
                    editText.setSelection(editText.length());
                    touchNum++;
                } else if (touchNum == 4){
                    touch3.clearAnimation();
                    editText.setText("네");
                    editText.setSelection(editText.length());
                    touchNum = 0;
                }
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchNum == 5){
                    editText.setText("ㄱ");
                    editText.setSelection(editText.length());
                    touchNum++;
                } else if (touchNum == 6){
                    editText.setText("ㅋ");
                    editText.setSelection(editText.length());
                    touchNum++;
                } else if (touchNum == 7){
                    touch4.clearAnimation();
                    touch5.startAnimation(scale3);
                    editText.setText("ㄲ");
                    editText.setSelection(editText.length());
                    touchNum++;
                }
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchNum == 8){
                    touch5.clearAnimation();
                    touch6.startAnimation(scale3);
                    editText.setText("끄");
                    editText.setSelection(editText.length());
                    touchNum++;
                }
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchNum == 9){
                    editText.setText("끋");
                    editText.setSelection(editText.length());
                    touchNum++;
                } else if (touchNum == 10){
                    touch6.clearAnimation();
                    editText.setText("끝");
                    editText.setSelection(editText.length());
                    touchNum = 0;
                }
            }
        });
    }

    protected void clickMethod(int position){
        if (position == -1){
            Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
            intent.putExtra("toastNumber", 2);
            startActivityForResult(intent, AppConstants.REQUEST_CODE_KEYPADPAGE2);

            number = 0;
        } else if (position == 0){
            layout1.setVisibility(View.VISIBLE);
            isVisibilityView(layout2);
        } else if (position == 1){
            isVisibilityView(layout1);
            isVisibilityView(layout3);
            layout2.setVisibility(View.VISIBLE);

        } else if (position == 2){
            animNext = 0;

            stopAnim();
            isVisibilityView(layout2);
            layout3.setVisibility(View.VISIBLE);
            isVisibilityView(layout4);
            editText.setText("");
            resetViews(image1s, text1s);
            resetViews(image2s, text2s);

            touchNum = 0;
            stopsign = true;

            touch1.setTextColor(Color.parseColor("#FF0000"));
            touch2.setTextColor(Color.parseColor("#FF0000"));
            touch3.setTextColor(Color.parseColor("#FF0000"));

            if (AppConstants.TYPING_O_ifValue1){
                ex_layout2.setVisibility(View.VISIBLE);
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (number == 2){
                            touch1.startAnimation(scale1);
                        }
                    }
                }, 3000);
            }
            //설명 띄울지

        } else if (position == 3){
            animNext = 1;

            layout4.setVisibility(View.VISIBLE);
            editText.setText("");
            resetViews(image1s, text1s);
            resetViews(image2s, text2s);
            stopAnim();

            text.setText("[ 네 ]");
            touchNum = 1;
            stopsign = false;

            touch1.setTextColor(Color.parseColor("#88FF0000"));
            touch2.setTextColor(Color.parseColor("#88FF0000"));
            touch3.setTextColor(Color.parseColor("#88FF0000"));

            if (AppConstants.TYPING_O_ifValue2){
                ex_layout3.setVisibility(View.VISIBLE);
            }
            touch1.startAnimation(scale3);
            //설명 띄울지

        } else if (position == 4){
            animNext = 0;

            isVisibilityView(layout4);
            resetViews(image2s, text2s);
            resetViews(image1s, text1s);
            stopAnim();

            text.setText("[ 끝 ]");
            editText.setText("");

            touch4.setTextColor(Color.parseColor("#FF0000"));
            touch5.setTextColor(Color.parseColor("#FF0000"));
            touch6.setTextColor(Color.parseColor("#FF0000"));

            touchNum = 0;
            stopsign = true;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (number == 4){
                        touch4.startAnimation(scale1);

                    }
                }
            }, 3000);

        } else if (position == 5){
            animNext = 1;

            layout4.setVisibility(View.VISIBLE);
            editText.setText("");
            resetViews(image2s, text2s);

            touchNum = 5;
            stopsign = false;
            stopAnim();

            touch4.setTextColor(Color.parseColor("#88FF0000"));
            touch5.setTextColor(Color.parseColor("#88FF0000"));
            touch6.setTextColor(Color.parseColor("#88FF0000"));

            touch4.startAnimation(scale3);

        } else if (position == 6){
            Intent intent = new Intent(getApplicationContext(), Typing_one_Quiz.class);
            startActivity(intent);
            finish();
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

        if (requestCode == AppConstants.REQUEST_CODE_MAIN){
            AppConstants.KeypadPage2_ifValue = false;
        } else if (requestCode == AppConstants.REQUEST_CODE_KEYPADPAGE2){
            finish();
        } else if (requestCode == AppConstants.REQUEST_CODE_BACK){
            if (resultCode == RESULT_CANCELED){
                finish();
            }
        }

    }

    protected void touch_scale2(int num){
        final int i = num;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                touchs[i].startAnimation(scale2);
            }
        }, 300);
    }

    protected void touch_scale(int num){
        final int i = num;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                touchs[i].startAnimation(scale1);
            }
        }, 300);
    }

    class animListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (stopsign){
                if (number == 2 || number == 3){
                    if (animNum == 0){
                        editText.setText("ㄴ");
                        editText.setSelection(editText.length());

                        image1s[0].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale2(1);
                    } else if (animNum == 2){
                        editText.setText("너");
                        editText.setSelection(editText.length());

                        image1s[2].setVisibility(View.VISIBLE);
                        text1s[1].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale2(2);
                    }
                } else if (number == 4 || number == 5) {
                    if (animNum == 0) {
                        editText.setText("ㄱ");
                        editText.setSelection(editText.length());

                        image2s[0].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale2(3);
                    } else if (animNum == 2) {
                        editText.setText("ㄲ");
                        editText.setSelection(editText.length());

                        image2s[2].setVisibility(View.VISIBLE);
                        text2s[1].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale2(4);
                    } else if (animNum == 4) {
                        editText.setText("끋");
                        editText.setSelection(editText.length());

                        image2s[4].setVisibility(View.VISIBLE);
                        text2s[3].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale2(5);
                    }
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    class animListener2 implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            if (stopsign) {
                if (number == 2 || number == 3){
                    if (animNum == 1){
                        editText.setText("ㄴ·");
                        editText.setSelection(editText.length());

                        image1s[1].setVisibility(View.VISIBLE);
                        text1s[0].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale(2);
                    } else if (animNum == 3){
                        editText.setText("네");
                        editText.setSelection(editText.length());

                        image1s[3].setVisibility(View.VISIBLE);
                        text1s[2].setVisibility(View.VISIBLE);
                        animNum = 0;
                        animNext = 1;

                        if (number == 3){
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    editText.setText("");
                                    touch1.startAnimation(scale3);
                                    resetViews(image1s, text1s);
                                    touch1.setTextColor(Color.parseColor("#88FF0000"));
                                    touch2.setTextColor(Color.parseColor("#88FF0000"));
                                    touch3.setTextColor(Color.parseColor("#88FF0000"));
                                    stopsign = false;
                                }
                            }, 1000);
                        }
                    }
                } else if (number == 4 || number == 5){
                    if (animNum == 1){
                        editText.setText("ㅋ");
                        editText.setSelection(editText.length());

                        image2s[1].setVisibility(View.VISIBLE);
                        text2s[0].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale(3);
                    } else if (animNum == 3) {
                        editText.setText("끄");
                        editText.setSelection(editText.length());

                        image2s[3].setVisibility(View.VISIBLE);
                        text2s[2].setVisibility(View.VISIBLE);
                        animNum++;
                        touch_scale(5);
                    } else if (animNum == 5) {
                        editText.setText("끝");
                        editText.setSelection(editText.length());

                        image2s[5].setVisibility(View.VISIBLE);
                        text2s[4].setVisibility(View.VISIBLE);
                        animNum = 0;
                        animNext = 1;

                        if (number == 5){
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    editText.setText("");
                                    touch4.startAnimation(scale3);
                                    resetViews(image2s, text2s);
                                    touch4.setTextColor(Color.parseColor("#88FF0000"));
                                    touch5.setTextColor(Color.parseColor("#88FF0000"));
                                    touch6.setTextColor(Color.parseColor("#88FF0000"));
                                    stopsign = false;
                                }
                            }, 1000);
                        }
                    }
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    protected void resetViews(View[] views, View[] views2){
        for (View view : views){
            view.setVisibility(View.INVISIBLE);
        }
        for (View view2 : views2){
            view2.setVisibility(View.INVISIBLE);
        }
    }

    protected void stopAnim(){
        for(int i = 0; i < touchs.length; i++){
            touchs[i].clearAnimation();
            animNum = 0;
        }
    }
}
