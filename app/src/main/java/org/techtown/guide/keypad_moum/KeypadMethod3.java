package org.techtown.guide.keypad_moum;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;
import org.techtown.guide.ToastActivity;
import org.techtown.guide.keypad_jaum.KeypadPage;
import org.techtown.guide.main.MainActivity;

public class KeypadMethod3 extends Fragment {

    ViewGroup rootView;
    ViewGroup keypadpage2;

    TextView text;
    EditText editText;
    TextView touch;
    TextView touch2;
    TextView touch3;

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout root1;
    LinearLayout root2;
    LinearLayout root3;

    ImageView[] root1images;
    TextView[] root1text;

    ImageView[] root2images;
    TextView[] root2texts;

    ImageView[] root3images;
    TextView[] root3texts;

    Animation scale;
    Animation scale2;
    Animation alpha;

    static int number = 0;
    int root2num = 0;
    int root3num = 0;
    int animEnd = 1;

    FrameLayout ex_layout1;

    Handler handler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.keypad_method_3, container, false);
        keypadpage2 = container;

        text = rootView.findViewById(R.id.text);

        editText = rootView.findViewById(R.id.editText);
        editText.setShowSoftInputOnFocus(false);

        layout1 = rootView.findViewById(R.id.layout1);
        layout2 = rootView.findViewById(R.id.layout2);
        root1 = rootView.findViewById(R.id.root1);
        root2 = rootView.findViewById(R.id.root2);
        root3 = rootView.findViewById(R.id.root3);
        touch = rootView.findViewById(R.id.touch);
        touch2 = rootView.findViewById(R.id.touch2);
        touch3 = rootView.findViewById(R.id.touch3);


        root1images = new ImageView[]{rootView.findViewById(R.id.root1_image1), rootView.findViewById(R.id.root1_image2)};
        root1text = new TextView[]{rootView.findViewById(R.id.root1_text1)};

        root2images = new ImageView[]{rootView.findViewById(R.id.root2_image1), rootView.findViewById(R.id.root2_image2),
                                      rootView.findViewById(R.id.root2_image3)};
        root2texts = new TextView[]{rootView.findViewById(R.id.root2_text1), rootView.findViewById(R.id.root2_text2)};

        root3images = new ImageView[]{rootView.findViewById(R.id.root3_image1), rootView.findViewById(R.id.root3_image2),
                rootView.findViewById(R.id.root3_image3), rootView.findViewById(R.id.root3_image4), rootView.findViewById(R.id.root3_image5)};
        root3texts = new TextView[]{rootView.findViewById(R.id.root3_text1), rootView.findViewById(R.id.root3_text2),
                rootView.findViewById(R.id.root3_text3), rootView.findViewById(R.id.root3_text4)};

        setImageVisibilitys(root1images, root2images, root3images);
        setTextVisibilitys(root1text, root2texts, root3texts);

        scaleAnimationListener listener1 = new scaleAnimationListener();
        scale2AnimationListener listener2 = new scale2AnimationListener();

        scale = AnimationUtils.loadAnimation(getContext(), R.anim.scale_keypad);
        scale.setAnimationListener(listener1);
        scale2 = AnimationUtils.loadAnimation(getContext(), R.anim.scale_keypad);
        scale2.setAnimationListener(listener2);
        alpha = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_keypad);

        ex_layout1 = rootView.findViewById(R.id.ex_Layout1);
        ex_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex_layout1.setVisibility(View.INVISIBLE);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touch.startAnimation(scale);
                    }
                }, 3000);

                AppConstants.KeypadPage2_ifValue2 = false;

            }
        });
        Button backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animEnd == 1){
                    number--;
                    clickMethod(number);
                }
            }
        });

        Button nextButton = rootView.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animEnd == 1){
                    number++;
                    clickMethod(number);
                }
            }
        });

        Button reviewButton = rootView.findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animEnd == 1){
                    editText.setText("");
                    animEnd = 0;
                    if (number == 1){
                        setImageVisibility(root1images);
                        setTextVisibility(root1text);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch.startAnimation(scale);
                            }
                        }, 500);

                    } else if (number == 2){
                        setImageVisibility(root2images);
                        setTextVisibility(root2texts);

                        root2num = 1;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch2.startAnimation(scale);
                            }
                        }, 500);
                    } else if (number == 3){
                        setImageVisibility(root3images);
                        setTextVisibility(root3texts);

                        root3num = 1;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                touch2.startAnimation(scale);
                            }
                        }, 500);
                    }
                }
            }
        });

        return rootView;
    }
    public void clickMethod(int position){

        editText.setText("");
        animEnd = 0;

        if (position == -1){

            keypadpage2.findViewById(R.id.fragment_layout).setVisibility(View.INVISIBLE);
            number = 0;
            animEnd = 1;

        } else if (position == 0) {

            layout1.setVisibility(View.VISIBLE);
            isVisibilityView(layout2);
            animEnd = 1;

        } else if (position == 1){

            setImageVisibility(root1images);
            setTextVisibility(root1text);

            isVisibilityView(layout1);
            isVisibilityView(root2);
            layout2.setVisibility(View.VISIBLE);
            root1.setVisibility(View.VISIBLE);
            text.setText("'ㅏ'");

            if (AppConstants.KeypadPage2_ifValue2){
                ex_layout1.setVisibility(View.VISIBLE);
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touch.startAnimation(scale);
                    }
                }, 3000);
            }

        } else if (position == 2){

            setImageVisibility(root2images);
            setTextVisibility(root2texts);

            isVisibilityView(root1);
            isVisibilityView(root3);
            root2.setVisibility(View.VISIBLE);
            text.setText("'ㅛ'");

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    touch2.startAnimation(scale);
                }
            }, 3000);

            root2num = 1;

        } else if (position == 3) {

            setImageVisibility(root3images);
            setTextVisibility(root3texts);

            isVisibilityView(root2);
            root3.setVisibility(View.VISIBLE);
            text.setText("'ㅙ'");

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    touch2.startAnimation(scale);
                }
            }, 3000);

            root3num = 1;

        } else if (position == 4){
            number = 3;
            getActivity().findViewById(R.id.fragment_layout2).setVisibility(View.VISIBLE);
        }
    }

    public void isVisibilityView(View view){

        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void setImageVisibilitys(ImageView[] view1, ImageView[] view2, ImageView[] view3){
        for (ImageView view : view1){
            view.setVisibility(View.INVISIBLE);
        }
        for (ImageView view : view2){
            view.setVisibility(View.INVISIBLE);
        }
        for (ImageView view : view3){
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void setImageVisibility(ImageView[] view1) {
        for (ImageView view : view1) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void setTextVisibilitys(TextView[] text1, TextView[] text2, TextView[] text3){
        for (TextView view : text1){
            view.setVisibility(View.INVISIBLE);
        }
        for (TextView view : text2){
            view.setVisibility(View.INVISIBLE);
        }
        for (TextView view : text3){
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void setTextVisibility(TextView[] text1) {
        for (TextView view : text1) {
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
                    root1images[0].setVisibility(View.VISIBLE);
                    editText.setText("ㅣ");
                    editText.setSelection(editText.length());
                    touch2.startAnimation(scale2);
            } else if (number == 2){
                if (root2num == 1){
                    root2images[0].setVisibility(View.VISIBLE);
                    editText.setText("·");
                    editText.setSelection(editText.length());
                    touch2.startAnimation(scale2);
                } else if (root2num == 2){
                    root2texts[1].setVisibility(View.VISIBLE);
                    root2images[2].setVisibility(View.VISIBLE);
                    editText.setText("ㅛ");
                    editText.setSelection(editText.length());
                    animEnd = 1;
                }
            } else if (number == 3){
                if (root3num == 1) {
                    root3images[0].setVisibility(View.VISIBLE);
                    editText.setText("·");
                    editText.setSelection(editText.length());
                    touch3.startAnimation(scale2);
                    root3num++;
                } else if (root3num == 3){
                    root3texts[1].setVisibility(View.VISIBLE);
                    root3images[2].setVisibility(View.VISIBLE);
                    editText.setText("ㅚ");
                    editText.setSelection(editText.length());
                    touch2.startAnimation(scale2);
                    root3num++;
                } else if (root2num == 2){
                    root3texts[3].setVisibility(View.VISIBLE);
                    root3images[4].setVisibility(View.VISIBLE);
                    editText.setText("ㅙ");
                    editText.setSelection(editText.length());
                    animEnd = 1;
                }
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
            if (number == 1){
                root1text[0].setVisibility(View.VISIBLE);
                root1images[1].setVisibility(View.VISIBLE);
                editText.setText("ㅏ");
                editText.setSelection(editText.length());
                animEnd = 1;
            } else if (number == 2){
                root2texts[0].setVisibility(View.VISIBLE);
                root2images[1].setVisibility(View.VISIBLE);
                editText.setText("··");
                editText.setSelection(editText.length());
                touch3.startAnimation(scale);
                root2num = 2;
            } else if (number == 3){
                if (root3num == 2){
                    root3texts[0].setVisibility(View.VISIBLE);
                    root3images[1].setVisibility(View.VISIBLE);
                    editText.setText("ㅗ");
                    editText.setSelection(editText.length());
                    touch.startAnimation(scale);
                    root3num++;
                } else if (root3num == 4){
                    root3texts[2].setVisibility(View.VISIBLE);
                    root3images[3].setVisibility(View.VISIBLE);
                    editText.setText("ㅘ");
                    editText.setSelection(editText.length());
                    touch.startAnimation(scale);
                    root3num++;
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
