package com.hhmedic.android.hhdoctorvideodemo.application;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.util.Log;


import com.hhmedic.android.hhdoctorvideodemo.activity.LocalConfig;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.config.DeviceType;
import com.hhmedic.android.sdk.config.HHSDKOptions;
import com.hhmedic.android.sdk.medicine.HHMedicine;

public class DoctorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK();
    }

    private void initSDK() {

        String pid = LocalConfig.getPid(this);
        if (TextUtils.isEmpty(pid)) {
            pid = "9002";
        }
        HHSDKOptions options = new HHSDKOptions(pid); //productId是视频医生提供方分配的产品Id
        options.isDebug = true;
        options.dev = LocalConfig.isDevelop(this);
        HHDoctor.init(getApplicationContext(), options);
        HHMedicine.init();
    }
}
