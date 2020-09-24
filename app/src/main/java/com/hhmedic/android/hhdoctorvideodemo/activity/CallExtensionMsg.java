package com.hhmedic.android.hhdoctorvideodemo.activity;

import java.io.Serializable;

public class CallExtensionMsg implements Serializable {
    public String ext;

    public CallExtensionMsg(String msg) {
        ext = msg;
    }
}
