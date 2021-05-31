package com.example.vetsertification.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vetsertification.R;
import com.example.vetsertification.ui.mainpage.MainPageView;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainPageView.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}