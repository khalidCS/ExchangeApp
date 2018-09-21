package com.droidman.exhangeapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.droidman.exhangeapp.common.Constants;
import com.droidman.exhangeapp.R;
import com.droidman.exhangeapp.common.SharedPreferencesUtils;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(isFirstOpen()){
                    SharedPreferencesUtils.getInstance(getApplicationContext()).setBoolean(Constants.SHARED_PREF_KEY_IS_FIRST_TIME, false);
                    Intent viewPagerIntent = new Intent(SplashActivity.this, ViewPagerActivity.class);
                    startActivity(viewPagerIntent);
                    finish();
                }else{
                    // delete local DB then get data from WS store it to local DB
                    Intent mainIntent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }

            private boolean isFirstOpen() {
                return SharedPreferencesUtils.getInstance(getApplicationContext()).getBoolean(Constants.SHARED_PREF_KEY_IS_FIRST_TIME, Constants.SHARED_PREF_DEFAULT_VALUE_IS_FIRST_TIME);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}