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
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.imsdk.v2.V2TIMAdvancedMsgListener;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;

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

    private void initTim() {
        V2TIMManager.getInstance().initSDK(getApplicationContext(), 1400443520, new V2TIMSDKConfig(), new V2TIMSDKListener() {
            @Override
            public void onConnecting() {
                super.onConnecting();
            }

            @Override
            public void onConnectSuccess() {
                super.onConnectSuccess();

            }

            @Override
            public void onConnectFailed(int code, String error) {

                Logger.e("TIM:onDisconnected");

            }

            @Override
            public void onKickedOffline() {
                Logger.e("user onForceOffline");
            }

            @Override
            public void onUserSigExpired() {

                Logger.e("user onUserSigExpired");

            }

            @Override
            public void onSelfInfoUpdated(V2TIMUserFullInfo info) {
                super.onSelfInfoUpdated(info);
            }
        });


        V2TIMManager.getMessageManager().addAdvancedMsgListener(new V2TIMAdvancedMsgListener(){


            @Override
            public void onRecvNewMessage(V2TIMMessage msg) {


                try {

                    long time = V2TIMManager.getInstance().getServerTime();

                    long messageTime = msg.getTimestamp();

                    Logger.e("serverTime ----->"+time + "     messageTime ---->"+messageTime + "  current --->"+ System.currentTimeMillis()/1000);

                    if(time -messageTime > 80){

                        Logger.e("message time is long drop it");

                        return;
                    }

                    if (!msg.isRead()){

                        V2TIMManager.getMessageManager().markC2CMessageAsRead(msg.getUserID(), new V2TIMCallback() {
                            @Override
                            public void onSuccess() {

                                Logger.e("markC2CMessageAsRead success");
                            }

                            @Override
                            public void onError(int code, String desc) {

                                Logger.e("markC2CMessageAsRead error:"+code);

                            }
                        });
                    }

                }catch (Exception e){

                    Logger.e("onRecvNewMessage error:"+e.getMessage());
                }

            }
        });
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

//        options.localRenderRotation = TRTCCloudDef.TRTC_VIDEO_ROTATION_90;
        MessageOptions messageOptions = new MessageOptions();
        messageOptions.hideUserCenter = LocalConfig.getEnableUserCenter(context);
        messageOptions.isFilterSummary = LocalConfig.getEnableSummaryCard(context);
        messageOptions.isFilterMedicinal = LocalConfig.getEnableMedicalCard(context);
        messageOptions.enableBuyService = LocalConfig.getEnableCanBuy(context);
        options.messageOptions = messageOptions;


//        VideoSetting.setEnableGSENSORMode(false);

//        VideoSetting.setRemoteRotation(TRTCCloudDef.TRTC_VIDEO_ROTATION_90);

        HHDoctor.init(context, options);
    }
}
