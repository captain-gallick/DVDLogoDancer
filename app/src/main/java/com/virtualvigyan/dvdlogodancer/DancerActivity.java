package com.virtualvigyan.dvdlogodancer;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class DancerActivity extends AppCompatActivity {

    boolean backPressed = false;
    ImageView dvdImage;
    RelativeLayout mainLayout;

    float width, height;
    int count = 0;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dancer);

        dvdImage = findViewById(R.id.dvd_yellow);
        mainLayout = findViewById(R.id.main_layout);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        dvdImage.setX(width-120);
        dvdImage.setY(height/2);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                switch (count){
                    case 0:
                        count+=1;
                        dvdImage.animate().x((width/2)-60).y(0).setDuration(4000);
                        dvdImage.setImageResource(R.drawable.dvd_yellow);
                        break;
                    case 1:
                        count+=1;
                        dvdImage.animate().x(0).y((float) (height/2-35)).setDuration(4000);
                        dvdImage.setImageResource(R.drawable.dvd_blue);
                        break;
                    case 2:
                        count+=1;
                        dvdImage.animate().x((width/2)-60).y(height-70).setDuration(4000);
                        dvdImage.setImageResource(R.drawable.dvd_red);
                        break;
                    case 3:
                        count = 0;
                        dvdImage.animate().x(width-120).y(height/2-35).setDuration(4000);
                        dvdImage.setImageResource(R.drawable.dvd_green);
                        break;
                }
                // Run again in 5s.
                handler.postDelayed(this, 4000);
            }
        };
        handler.postDelayed(runnable, 0);



    }

    private void animatorMethod(){
        if (count==0){
            dvdImage.animate().x(width/2).y(height).setDuration(5000);
            Log.d("count",String.valueOf(count));
        }else if(count==1){
            dvdImage.animate().x(0).y(height/2).setDuration(5000);
            Log.d("count",String.valueOf(count));
        }else if(count==2){
            dvdImage.animate().x(width/2).y(0).setDuration(5000);
            Log.d("count",String.valueOf(count));
        }else if(count==3){
            dvdImage.animate().x(width).y(height/2).setDuration(5000);
            Log.d("count",String.valueOf(count));
            count=0;
        }
    }

    @Override
    public void onBackPressed() {

        if (backPressed){
            finish();
            return;
        }

        backPressed = true;
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        }, 2000);

    }
}

