package com.hhmedic.android.hhdoctorvideodemo.application;

import android.app.Application;
import android.content.Context;
import android.hardware.Camera;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.hhmedic.android.hhdoctorvideodemo.activity.LocalConfig;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.config.DeviceType;
import com.hhmedic.android.sdk.config.HHSDKOptions;
import com.orhanobut.logger.Logger;
//import com.hhmedic.android.sdk.medicine.HHMedicine;

public class DoctorApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

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
        options.enableAddMember = LocalConfig.getEnableAddMember(this);
        options.enableMultiCall = LocalConfig.getEnableMultiCall(this);
        options.messageTitle = LocalConfig.getMessageTitle(this);
        options.enableMedical = LocalConfig.getEnableMedical(this);
        options.enableActivate = LocalConfig.getEnableActivate(this);
//        options.useSampleRate16K_HZ = true;
//        options.enableHighQualityMusic = true;
        HHDoctor.init(getApplicationContext(), options);
//        HHMedicine.init();
    }
}
