package org.techtown.guide.keypad_jaum;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;

public class KeypadMethod extends Fragment {
    ViewGroup rootView;

    static int number = 0;
    int a = 0;
    int b = 0;

    TextView textView;
    ImageView circleLeft;
    ImageView circleRight;
    TextView circleText;
    LinearLayout imageLayout;
    LinearLayout exLayout;
    EditText editText;
    ImageView keypad;
    TextView touch;

    Animation scale;
    Animation scale2;

    LinearLayout linearLayout;

    ViewGroup keypadmethod_layout;

    Handler handler = new Handler();
    int animNext = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.keypad_method, container, false);

        keypadmethod_layout = container;

        textView = rootView.findViewById(R.id.text);
        circleLeft = rootView.findViewById(R.id.circle_left);
        circleRight = rootView.findViewById(R.id.circle_right);
        circleText = rootView.findViewById(R.id.circle_text);
        imageLayout = rootView.findViewById(R.id.imagelayout);
        exLayout = rootView.findViewById(R.id.exLayout);

        editText = rootView.findViewById(R.id.editText);
        editText.setShowSoftInputOnFocus(false);

        linearLayout = rootView.findViewById(R.id.linearlayout);
        keypad = rootView.findViewById(R.id.imageView2);
        touch = rootView.findViewById(R.id.touch);
        scale = AnimationUtils.loadAnimation(getContext(), R.anim.scale_keypad);
        scale2 = AnimationUtils.loadAnimation(getContext(), R.anim.scale_keypad);

        scaleAnimationListener listener = new scaleAnimationListener();
        scale.setAnimationListener(listener);

        scale2AnimationListener listener2 = new scale2AnimationListener();
        scale2.setAnimationListener(listener2);


        Button back = rootView.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("1818", "number : " + number);
                if (b == 0){
                    number--;
                    clickMethod(number);
                }
            }
        });

        Button next = rootView.findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (b == 0){
                    number++;
                    clickMethod(number);
                }

            }
        });

        Button replay = rootView.findViewById(R.id.replayButton);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (animNext == 1){
                    animNext = 0;

                    editText.setText("");
                    b = 1;

                    if (number == 1){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch.startAnimation(scale);
                            }
                        }, 500);
                    } else if (number == 2){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch.startAnimation(scale2);
                            }
                        }, 500);

                    } else if (number == 3){
                        a = 0;

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch.startAnimation(scale2);
                            }
                        }, 500);
                    }
                }

            }
        });

        exLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exLayout.setVisibility(View.INVISIBLE);

                AppConstants.KeypadMethod_ifValue = false;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touch.startAnimation(scale);
                    }
                }, 2000);
            }
        });


        return rootView;
    }

    public void clickMethod(int position){

        editText.setText("");
        b = 1;

        if (position == -1){
            keypadmethod_layout.findViewById(R.id.keylayout).setVisibility(View.INVISIBLE);
            KeypadPage.toolbar.setTitle("키패드 입력 방법");

            number = 0;
            b = 0;
        } else if (position == 0) {
            textView.setText("키패드의 빨간 네모 안에는");

            linearLayout.setVisibility(View.VISIBLE);
            isVisibilityView(imageLayout);
            isVisibilityView(circleLeft);
            isVisibilityView(circleRight);
            isVisibilityView(circleText);
            keypad.setImageResource(R.drawable.keypad_jaum);
            isVisibilityView(touch);

            b = 0;
        } else if (position == 1){
            animNext = 0;

            textView.setText("자판을 한번 누르면 ㄱ이 입력됨.");
            imageLayout.setVisibility(View.VISIBLE);
            circleLeft.setVisibility(View.VISIBLE);
            isVisibilityView(linearLayout);
            isVisibilityView(circleRight);
            isVisibilityView(touch);

            keypad.setImageResource(R.drawable.keypad);

            if (AppConstants.KeypadMethod_ifValue){
                exLayout.setVisibility(View.VISIBLE);
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touch.startAnimation(scale);
                    }
                }, 2000);
            }

        } else if (position == 2){
            animNext = 0;

            textView.setText("연속 두번 누르면 ㅋ이 입력됨.");
            isVisibilityView(circleLeft);
            isVisibilityView(circleText);
            circleRight.setVisibility(View.VISIBLE);
            isVisibilityView(touch);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    touch.startAnimation(scale2);
                }
            }, 2000);

        } else if (position == 3) {
            a = 0;
            animNext = 0;

            textView.setText("연속 세번 누르면 ㄲ이 입력됨.");
            isVisibilityView(circleLeft);
            isVisibilityView(circleRight);
            isVisibilityView(touch);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    circleText.setVisibility(View.VISIBLE);
                }
            }, 3000);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    touch.startAnimation(scale2);
                }
            }, 2000);

        } else if (position == 4){
            getFragmentManager().beginTransaction().replace(R.id.keylayout2, new KeypadMethod2()).commit();
            getActivity().findViewById(R.id.keylayout2).setVisibility(View.VISIBLE);
            b = 0;
        }
    }

    public void isVisibilityView(View view){

        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }
    }

    class scaleAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (number == 1){
                editText.setText("ㄱ");
                editText.setSelection(editText.length());

                touch.setVisibility(View.VISIBLE);
                b = 0;
                animNext = 1;
            } else if (number == 2){
                editText.setText("ㅋ");
                editText.setSelection(editText.length());

                b = 0;
                animNext = 1;
            } else if (number == 3){
                editText.setText("ㅋ");
                editText.setSelection(editText.length());
                touch.startAnimation(scale2);
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    class scale2AnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (number == 2) {
                editText.setText("ㄱ");
                editText.setSelection(editText.length());
                touch.startAnimation(scale);
                touch.setVisibility(View.VISIBLE);
            } else if (number == 3) {
                if (a == 0){
                    a = 1;
                    editText.setText("ㄱ");
                    editText.setSelection(editText.length());
                    touch.startAnimation(scale);
                } else if (a == 1) {
                    editText.setText("ㄲ");
                    touch.setVisibility(View.VISIBLE);
                    editText.setSelection(editText.length());

                    b = 0;
                    animNext = 1;
                }
            }
        }


        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
