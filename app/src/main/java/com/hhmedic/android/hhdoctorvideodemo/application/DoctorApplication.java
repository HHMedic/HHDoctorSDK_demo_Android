package com.hhmedic.android.hhdoctorvideodemo.application;

import android.app.Application;
import android.content.pm.ActivityInfo;


import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.config.HHSDKOptions;

public class DoctorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK();
    }

    private void initSDK() {

        HHSDKOptions options = HHSDKOptions.defaultSoundOption("9002"); //productId是和缓分配的产品Id
        options.isDebug = true;
        options.dev = true;

        HHDoctor.init(getApplicationContext(), options);
    }
}
