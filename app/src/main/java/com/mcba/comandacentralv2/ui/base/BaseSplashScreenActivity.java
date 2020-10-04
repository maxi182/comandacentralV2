package com.mcba.comandacentralv2.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseSplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 500;

    private long mSplashScreenTimeout = SPLASH_SCREEN_TIME_OUT;

    protected abstract Intent proceedLoading();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newIntent = proceedLoading();
                if (newIntent != null) {
                    startActivity(newIntent);
                }
                finish();
            }
        }, mSplashScreenTimeout);

    }

    protected void setSplashScreenTimeout(long splashScreenTimeout) {
        mSplashScreenTimeout = splashScreenTimeout;
    }
}
