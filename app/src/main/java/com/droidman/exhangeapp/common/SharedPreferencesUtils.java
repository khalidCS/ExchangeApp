package com.droidman.exhangeapp.common;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {
    private static SharedPreferencesUtils utils = null;
    private SharedPreferences preferences;

    private SharedPreferencesUtils(Context context){
        preferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME,MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getInstance(Context context){
        if(utils == null)
            utils = new SharedPreferencesUtils(context);
        return utils;
    }

    public boolean getBoolean(String sharedKey, boolean defaultValue){
        return preferences.getBoolean(sharedKey, defaultValue);
    }

    public void setBoolean(String sharedKey, boolean value){
        preferences.edit().putBoolean(sharedKey, value).apply();
    }
}
