package com.hhmedic.android.hhdoctorvideodemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.hhmedic.android.sdk.HHDoctor;
import com.orhanobut.logger.Logger;

/**
 * Created by iOS on 2018/3/19.
 *
 *
 */

public class Controller extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Logger.e("get controller");

        String command = intent.getStringExtra("command");

        if (!TextUtils.isEmpty(command))
        {
            HHDoctor.hangUp();
        }
    }
}
