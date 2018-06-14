package com.hhmedic.android.hhdoctorvideodemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.module.video.avchat.sound.Refuse;

import com.orhanobut.logger.Logger;

public class RefuseReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            doRefuse(context,intent);
        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }
    }

    private void doRefuse(Context context,  Intent intent)
    {
        String json = intent.getStringExtra("message");
        HHDoctor.onRefuse(context, json, new Refuse.OnCallback() {
            @Override
            public void onSuccess() {
                successTips(context,"已经拒绝接听");
            }

            @Override
            public void onError(String s) {

            }
        });
    }


    protected void successTips(Context context,String tips)
    {
        try
        {
            Toast toast =  Toast.makeText(context,tips,Toast.LENGTH_SHORT);

            toast.setGravity(Gravity.CENTER, 0, 0);

            toast.show();

        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }

    }

}
