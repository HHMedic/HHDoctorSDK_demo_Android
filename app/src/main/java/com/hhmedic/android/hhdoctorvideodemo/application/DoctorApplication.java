package com.hhmedic.android.hhdoctorvideodemo.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.hhmedic.android.hhdoctorvideodemo.activity.HHSDKConfig;
import com.hhmedic.android.hhdoctorvideodemo.activity.LocalConfig;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.VideoSetting;
import com.hhmedic.android.sdk.config.HHSDKOptions;
import com.hhmedic.android.sdk.config.MessageOptions;
import com.tencent.trtc.TRTCCloudDef;
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
            pid = HHSDKConfig.pid;
        }
        HHSDKOptions options = new HHSDKOptions(pid); //productId是视频医生提供方分配的产品Id
        options.isDebug = true;
        options.dev = LocalConfig.isDevelop(this);
        options.enableAddMember = LocalConfig.getEnableAddMember(this);
        options.messageTitle = LocalConfig.getMessageTitle(this);
        options.enableMedical = LocalConfig.getEnableMedical(this);
        options.enableActivate = LocalConfig.getEnableActivate(this);
        options.enableVipInfo = LocalConfig.getEnableVipInfo(this);
        options.enableAddMemberInDoc = LocalConfig.getEnableAddMemberInDoc(this);
        MessageOptions messageOptions = new MessageOptions();
        messageOptions.hideUserCenter = LocalConfig.getEnableUserCenter(this);
        messageOptions.isFilterSummary = LocalConfig.getEnableSummaryCard(this);
        messageOptions.isFilterMedicinal = LocalConfig.getEnableMedicalCard(this);
        options.messageOptions = messageOptions;
        HHDoctor.init(getApplicationContext(), options);

//        HHMedicine.init();
    }
}
