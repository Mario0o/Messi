package com.adu.messi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by adu on 2016/10/15.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SharedPreferences sp =getSharedPreferences("adu",MODE_PRIVATE);
        boolean isNight  = sp.getBoolean("night",false);
        if(isNight){
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static Context getContext(){
        return context;
    }
}
