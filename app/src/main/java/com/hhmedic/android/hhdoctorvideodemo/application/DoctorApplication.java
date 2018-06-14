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

        HHSDKOptions options = HHSDKOptions.defaultSoundOption("productId"); //productId是和缓分配的产品Id
        options.isDebug = true;
        options.mImei = "xxxxxxxxxxxxxx";//设备编号，多用于音箱设备对接
        options.dev = true;
        options.mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        HHDoctor.init(getApplicationContext(), options);
    }
}
