package com.hhmedic.android.hhdoctorvideodemo.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.hhmedic.android.hhdoctorvideodemo.activity.HHSDKConfig;
import com.hhmedic.android.hhdoctorvideodemo.activity.LocalConfig;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.VideoSetting;
import com.hhmedic.android.sdk.config.HHConfig;
import com.hhmedic.android.sdk.config.HHSDKOptions;
import com.hhmedic.android.sdk.config.MessageOptions;
import com.tencent.bugly.Bugly;
import com.tencent.trtc.TRTCCloudDef;

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

        Bugly.init(getApplicationContext(), "f9b08d0fa3", false);
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
        options.enableMultiCall = LocalConfig.getEnableMultiCall(this);
        options.messageTitle = LocalConfig.getMessageTitle(this);
        options.enableMedical = LocalConfig.getEnableMedical(this);
        options.enableActivate = LocalConfig.getEnableActivate(this);
        options.enableVipInfo = LocalConfig.getEnableVipInfo(this);
        options.enableAddMemberInDoc = LocalConfig.getEnableAddMemberInDoc(this);

        options.enableCloseCamera = LocalConfig.getHideCameraControl(this);
        options.isCloseCameraCall = LocalConfig.getCloseCameraCall(this);

//        options.localRenderRotation = TRTCCloudDef.TRTC_VIDEO_ROTATION_90;
        MessageOptions messageOptions = new MessageOptions();
        messageOptions.hideUserCenter = LocalConfig.getEnableUserCenter(this);
        messageOptions.isFilterSummary = LocalConfig.getEnableSummaryCard(this);
        messageOptions.isFilterMedicinal = LocalConfig.getEnableMedicalCard(this);
        messageOptions.enableBuyService = LocalConfig.getEnableCanBuy(this);
        options.messageOptions = messageOptions;

//        VideoSetting.setEnableGSENSORMode(false);

//        VideoSetting.setRemoteRotation(TRTCCloudDef.TRTC_VIDEO_ROTATION_90);

        HHDoctor.init(getApplicationContext(), options);
    }
}
