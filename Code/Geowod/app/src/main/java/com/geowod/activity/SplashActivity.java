package com.geowod.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.geowod.R;

/**
 * Created by sony on 13-10-2015.
 */
public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
