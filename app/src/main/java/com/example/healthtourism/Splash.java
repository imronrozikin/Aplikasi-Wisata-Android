package com.example.healthtourism;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

public class Splash extends AppCompatActivity {
    private ImageView iv1;
    private ImageView iv2;

    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        iv1.startAnimation(myanim);
        iv2.startAnimation(myanim);
        final Intent i = new Intent(this, Login.class);
        Thread timer = new Thread(){
            public  void  run (){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
