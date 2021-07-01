package org.techtown.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ToastActivity extends AppCompatActivity {

    Handler handler = new Handler();
    LinearLayout toast1;
    LinearLayout toast2;
    LinearLayout toast3;
    LinearLayout toast4;
    LinearLayout toast5;
    LinearLayout toast6;
    LinearLayout toast7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_activity);

        ReturnThread thread = new ReturnThread();
        toast1 = findViewById(R.id.toast1);
        toast2 = findViewById(R.id.toast2);
        toast3 = findViewById(R.id.toast3);
        toast4 = findViewById(R.id.toast4);
        toast5 = findViewById(R.id.toast5);
        toast6 = findViewById(R.id.toast6);
        toast7 = findViewById(R.id.toast7);

        Intent intent = getIntent();
        int toast = intent.getIntExtra("toastNumber", 0);

        if (toast == 1){
            toast1.setVisibility(View.VISIBLE);
            thread.start();
        } else if (toast == 2){
            toast2.setVisibility(View.VISIBLE);
            thread.start();
        } else if (toast == 3){
            toast3.setVisibility(View.VISIBLE);
        } else if (toast == 4){
            toast4.setVisibility(View.VISIBLE);
            thread.start();
        } else if (toast == 5){
            toast5.setVisibility(View.VISIBLE);
            thread.start();
        } else if (toast == 6){
            toast6.setVisibility(View.VISIBLE);
            thread.start();
        } else if (toast == 7){
            toast7.setVisibility(View.VISIBLE);
        }

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);

                finish();
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);

                finish();
            }
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    class ReturnThread extends Thread{
        public void run(){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }
    }

    public void isVisibilityView(View view){

        if (view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }
    }

}
