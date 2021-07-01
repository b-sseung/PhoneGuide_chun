package org.techtown.guide.keypad_jaum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;

import java.util.ArrayList;

public class KeypadMethod2 extends Fragment {

    ViewGroup rootView;
    ViewGroup container2;

    LinearLayout layout1;
    LinearLayout layout2;
    FrameLayout exLayout;

    static int number = 0;

    ImageView arrow1;
    ImageView arrow2;
    ImageView arrow3;
    ImageView arrow4;
    ImageView arrow5;

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;

    TextView touch_text1;
    TextView touch_text2;
    TextView touch_text3;
    TextView touch_text4;
    TextView touch_text5;

    TextView touch1;
    TextView touch2;
    TextView touch3;
    TextView touch4;
    TextView touch5;

    ArrayList<ImageView> arrows = new ArrayList<ImageView>();
    ArrayList<TextView> texts = new ArrayList<TextView>();
    Animation scale;

    int touch1num = 0;
    int touch2num = 0;
    int touch3num = 0;
    int touch4num = 0;
    int touch5num = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.keypad_method_2, container, false);
        container2 = container;
        layout1 = rootView.findViewById(R.id.layout1);
        layout2 = rootView.findViewById(R.id.layout2);
        exLayout = rootView.findViewById(R.id.exLayout);

        arrow1 = rootView.findViewById(R.id.arrow1);
        arrow2 = rootView.findViewById(R.id.arrow2);
        arrow3 = rootView.findViewById(R.id.arrow3);
        arrow4 = rootView.findViewById(R.id.arrow4);
        arrow5 = rootView.findViewById(R.id.arrow5);

        text1 = rootView.findViewById(R.id.text1);
        text2 = rootView.findViewById(R.id.text2);
        text3 = rootView.findViewById(R.id.text3);
        text4 = rootView.findViewById(R.id.text4);
        text5 = rootView.findViewById(R.id.text5);

        touch_text1 = rootView.findViewById(R.id.touch_text1);
        touch_text2 = rootView.findViewById(R.id.touch_text2);
        touch_text3 = rootView.findViewById(R.id.touch_text3);
        touch_text4 = rootView.findViewById(R.id.touch_text4);
        touch_text5 = rootView.findViewById(R.id.touch_text5);

        touch1 = rootView.findViewById(R.id.touch1);
        touch2 = rootView.findViewById(R.id.touch2);
        touch3 = rootView.findViewById(R.id.touch3);
        touch4 = rootView.findViewById(R.id.touch4);
        touch5 = rootView.findViewById(R.id.touch5);

        arrows.add(arrow1);
        arrows.add(arrow2);
        arrows.add(arrow3);
        arrows.add(arrow4);
        arrows.add(arrow5);

        texts.add(text1);
        texts.add(text2);
        texts.add(text3);
        texts.add(text4);
        texts.add(text5);

        scale = AnimationUtils.loadAnimation(getContext(), R.anim.scale_keypad2);
        animStart();

        if (AppConstants.KeypadMethod2_ifValue){
            exLayout.setVisibility(View.VISIBLE);
        }

        exLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exLayout.setVisibility(View.INVISIBLE);
                AppConstants.KeypadMethod2_ifValue = false;

            }
        });

        touch_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch1num++;
                touchClick(touch1num, 0);
            }
        });
        touch_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch2num++;
                touchClick(touch2num, 1);
            }
        });
        touch_text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch3num++;
                touchClick(touch3num, 2);
            }
        });
        touch_text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch4num++;
                touchClick(touch4num, 3);
            }
        });
        touch_text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch5num++;
                touchClick(touch5num, 4);
            }
        });

        Button back = rootView.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                clickMethod(number);
            }
        });

        Button next = rootView.findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                number++;
                clickMethod(number);
            }
        });

        return rootView;
    }

    public void clickMethod(int position) {

        if (position == -1) {

            KeypadMethod.number = 3;
            container2.findViewById(R.id.keylayout2).setVisibility(View.INVISIBLE);

            number = 0;
            touchNumReset();
        } else if (position == 0) {

            if (layout1.getVisibility() == View.INVISIBLE){
                layout1.setVisibility(View.VISIBLE);
            }

            if (layout2.getVisibility() == View.VISIBLE){
                layout2.setVisibility(View.INVISIBLE);
            }
            animStart();
            touchNumReset();

        } else if (position == 1) {
            if (layout2.getVisibility() == View.INVISIBLE){
                layout2.setVisibility(View.VISIBLE);
            }
            if (layout1.getVisibility() == View.VISIBLE){
                layout1.setVisibility(View.INVISIBLE);
            }
            animStart();
            touchNumReset();

        } else if (position == 2) {

            getActivity().findViewById(R.id.keylayout3).setVisibility(View.VISIBLE);
            touchNumReset();

        }
    }

    public void animStart(){
        if (number == 0) {
            touch1.startAnimation(scale);
            touch2.startAnimation(scale);
            touch3.startAnimation(scale);
            touch4.clearAnimation();
            touch5.clearAnimation();
        } else if (number == 1) {
            touch1.clearAnimation();
            touch2.clearAnimation();
            touch3.clearAnimation();
            touch4.startAnimation(scale);
            touch5.startAnimation(scale);
        }
    }

    public void touchClick(int touchnum, int text){
        if (touchnum == 1){
            switch (text){
                case 0 :
                    arrows.get(text).setVisibility(View.VISIBLE);
                    textChanged(text, "ㄱ");
                    break;
                case 1 :
                    arrows.get(text).setVisibility(View.VISIBLE);
                    textChanged(text, "ㄷ");
                    break;
                case 2 :
                    arrows.get(text).setVisibility(View.VISIBLE);
                    textChanged(text, "ㅂ");
                    break;
                case 3 :
                    arrows.get(text).setVisibility(View.VISIBLE);
                    textChanged(text, "ㅅ");
                    break;
                case 4 :
                    arrows.get(text).setVisibility(View.VISIBLE);
                    textChanged(text, "ㅈ");
                    break;
            }
        } else if (touchnum == 2){
            switch (text){
                case 0 :
                    textChanged(text, "ㅋ");
                    break;
                case 1 :
                    textChanged(text, "ㅌ");
                    break;
                case 2 :
                    textChanged(text, "ㅍ");
                    break;
                case 3 :
                    textChanged(text, "ㅎ");
                    break;
                case 4 :
                    textChanged(text, "ㅊ");
                    break;
            }
        } else if (touchnum == 3){
            switch (text){
                case 0 :
                    textChanged(text, "ㄲ");
                    touch1.clearAnimation();
                    break;
                case 1 :
                    textChanged(text, "ㄸ");
                    touch2.clearAnimation();
                    break;
                case 2 :
                    textChanged(text, "ㅃ");
                    touch3.clearAnimation();
                    break;
                case 3 :
                    textChanged(text, "ㅆ");
                    touch4.clearAnimation();
                    break;
                case 4 :
                    textChanged(text, "ㅉ");
                    touch5.clearAnimation();
                    break;
            }
        }
    }

    public void textChanged(int textNum, String textData){
        texts.get(textNum).setText(textData);
    }

    public void touchNumReset(){
        touch1num = 0;
        touch2num = 0;
        touch3num = 0;
        touch4num = 0;
        touch5num = 0;
        for (int i = 0; i < arrows.size(); i++){
            arrows.get(i).setVisibility(View.INVISIBLE);
            texts.get(i).setText("");
        }
    }

}
