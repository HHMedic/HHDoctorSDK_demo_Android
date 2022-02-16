package com.hhmedic.android.hhdoctorvideodemo.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.hhmedic.android.hhdoctorvideodemo.activity.HHSDKConfig;
import com.hhmedic.android.hhdoctorvideodemo.activity.LocalConfig;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.config.HHSDKOptions;
import com.hhmedic.android.sdk.config.MessageOptions;
import com.tencent.bugly.Bugly;

public class DoctorApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK(getApplicationContext());
//        initTim();
        Bugly.init(getApplicationContext(), "f9b08d0fa3", false);
    }

    public static void initSDK(Context context) {

        String pid = LocalConfig.getPid(context);
        if (TextUtils.isEmpty(pid)) {
            pid = HHSDKConfig.pid;
        }
        HHSDKOptions options = new HHSDKOptions(pid); //productId是视频医生提供方分配的产品Id
        options.isDebug = true;
        options.dev = LocalConfig.isDevelop(context);
        options.enableAddMember = LocalConfig.getEnableAddMember(context);
        options.enableMultiCall = LocalConfig.getEnableMultiCall(context);
        options.messageTitle = LocalConfig.getMessageTitle(context);
        options.enableMedical = LocalConfig.getEnableMedical(context);
        options.enableActivate = LocalConfig.getEnableActivate(context);
        options.enableVipInfo = LocalConfig.getEnableVipInfo(context);
        options.enableAddMemberInDoc = LocalConfig.getEnableAddMemberInDoc(context);

        options.enableCloseCamera = LocalConfig.getHideCameraControl(context);
        options.isCloseCameraCall = LocalConfig.getCloseCameraCall(context);

        options.isCloseMoreFunc = LocalConfig.getCloseMoreFunc(context);

        MessageOptions messageOptions = new MessageOptions();
        messageOptions.hideUserCenter = LocalConfig.getEnableUserCenter(context);
        messageOptions.isFilterSummary = LocalConfig.getEnableSummaryCard(context);
        messageOptions.isFilterMedicinal = LocalConfig.getEnableMedicalCard(context);
        messageOptions.enableBuyService = LocalConfig.getEnableCanBuy(context);
        options.messageOptions = messageOptions;

        HHDoctor.init(context, options);
    }
}
