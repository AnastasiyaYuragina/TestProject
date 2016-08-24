package com.anastasiyayuragina.testproject;

import android.app.Application;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;


/**
 * Created by anastasiyayuragina on 8/1/16.
 *
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySingleton.initInstance();
        FlowManager.init(new FlowConfig.Builder(this).build());

    }
}
