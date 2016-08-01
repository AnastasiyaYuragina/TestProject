package com.anastasiyayuragina.testproject;

import android.app.Application;

/**
 * Created by anastasiyayuragina on 8/1/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySingleton.initInstance();
    }
}
