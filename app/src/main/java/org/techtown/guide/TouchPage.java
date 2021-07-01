package org.techtown.guide;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techtown.guide.main.MainActivity;

public class TouchPage extends AppCompatActivity {

    boolean running = false;

    Animation scale;
    ImageView imageView;
    Context context;

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_page);

        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("기본 터치 연습하기");
        setSupportActionBar(toolbar);

        scale = AnimationUtils.loadAnimation(this, R.anim.scale_frag_main);
        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN){
                    running = true;
                    progress = 0;
                    TouchThread touchThread = new TouchThread();
                    touchThread.start();
                    imageView.startAnimation(scale);
                } else if (action == MotionEvent.ACTION_UP){
                    running = false;
                    if  (progress == 50){

                        Intent intent = new Intent(getApplicationContext(), ToastActivity.class);
                        intent.putExtra("toastNumber", 1);
                        startActivityForResult(intent, AppConstants.REQUEST_CODE_TOUCH);

                    } else{

                        if (AppConstants.Level[0] != AppConstants.LEVEL_CLEAR) {
                            AppConstants.Level[0] = AppConstants.LEVEL_CLEAR;
                        }
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                return true;
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

        if (requestCode == AppConstants.REQUEST_CODE_BACK){
            if (resultCode == RESULT_CANCELED){
                finish();
            }
        }
    }

    class TouchThread extends Thread{

        @Override
        public void run() {

            while(running) {
                if (progress < 50) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("1818", String.valueOf(progress));
                    progress++;
                } else {
                    break;
                }
            }
        }
    }

}
