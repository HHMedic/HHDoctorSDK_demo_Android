package com.hhmedic.android.hhdoctorvideodemo.application;

import com.google.gson.Gson;
import com.hhmedic.android.hhdoctorvideodemo.activity.CallExtensionMsg;
import com.hhmedic.android.sdk.HHDoctor;

public class CallExtensionSetting {

    public static void setJsonExtMessage(String message) {
        CallExtensionMsg msg = new CallExtensionMsg(message);
        String json = new Gson().toJson(msg);
        HHDoctor.setExtension(json);
    }

    public static void setNormalExtMessage(String message) {
        HHDoctor.setExtension(message);
    }
}
