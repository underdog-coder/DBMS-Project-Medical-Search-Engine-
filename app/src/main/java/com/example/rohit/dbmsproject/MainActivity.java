package com.example.rohit.dbmsproject;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPager myPager;
    private LinearLayout linearLayout;
    private TextView[] textViews;
    private Button next;
    private Button previousB;
    private int currentPage;
    private  int flag = 0;
    private SharedPreference sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreference = new SharedPreference(getApplicationContext());
        if (!sharedPreference.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_main);
        myPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.myLayout);
        next = findViewById(R.id.next);
        previousB = findViewById(R.id.previous);

        SliderAdapter sliderAdapter = new SliderAdapter(this);
        myPager.setAdapter(sliderAdapter);
        dotIndicator(0);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage == 2){
                    launchHomeScreen();
                   finish();
                }
                myPager.setCurrentItem(currentPage+1);

            }
        });
        previousB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPager.setCurrentItem(currentPage-1);
            }
        });
        myPager.addOnPageChangeListener(viewListener);
    }
    public  void launchHomeScreen(){
        sharedPreference.setFirstTimeLaunch(false);
        final Intent intent = new Intent(this,SplashActivity.class);
        startActivity(intent);

       finish();
    }

    public void dotIndicator(int position){
        textViews = new TextView[3];
        linearLayout.removeAllViews();
        linearLayout.addView(previousB);
        for(int i = 0; i < 3; i++){
            textViews[i] = new TextView(this);
            textViews[i].setText(Html.fromHtml("&#8226;"));
            textViews[i].setTextSize(35);
            textViews[i].setTextColor(getResources().getColor((R.color.colorTransparentWhite)));
            linearLayout.addView(textViews[i]);
        }
        linearLayout.addView(next);
        if(textViews.length > 0){
            textViews[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            dotIndicator(position);
            currentPage = position;
            if(position == 0){
                next.setEnabled(true);
                previousB.setEnabled(false);
                previousB.setVisibility(View.INVISIBLE);
                next.setText("NEXT");
                previousB.setText("");
            }
            else if(position == 2){
                next.setEnabled(true);
                previousB.setEnabled(true);
                previousB.setVisibility(View.VISIBLE);
                next.setText("FINISH");
                previousB.setText("BACK");

            }
            else{
                next.setEnabled(true);
                previousB.setEnabled(true);
                previousB.setVisibility(View.VISIBLE);
                next.setText("NEXT ");
                previousB.setText("BACK");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}