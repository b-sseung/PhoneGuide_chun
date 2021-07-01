package org.techtown.guide.keypad_jaum;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.guide.AppConstants;
import org.techtown.guide.R;

public class Keypad_jaum_Ex_1 extends AppCompatActivity {

    FrameLayout touchlayout;
    LinearLayout touchlayout2;
    FrameLayout touchlayout3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_jaum_1);

        touchlayout = findViewById(R.id.touchLayout);
        touchlayout3 = findViewById(R.id.touchLayout3);

        touchlayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchlayout.setVisibility(View.INVISIBLE);
                touchlayout2.setVisibility(View.VISIBLE);
            }
        });

        touchlayout2 = findViewById(R.id.touchLayout2);

        touchlayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
