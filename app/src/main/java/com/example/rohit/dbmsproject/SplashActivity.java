package com.example.rohit.dbmsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView text = findViewById(R.id.font);
        ImageView logo = findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        text.startAnimation(animation);
        logo.startAnimation(animation);
        final Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
                super.run();
            }
        };
        th.start();

    }
}