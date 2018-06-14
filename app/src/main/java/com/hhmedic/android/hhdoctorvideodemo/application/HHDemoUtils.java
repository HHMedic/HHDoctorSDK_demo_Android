package com.hhmedic.android.hhdoctorvideodemo.application;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by iOS on 2018/3/21.
 *
 *
 */

public class HHDemoUtils
{

    public static void notice(Context context,String text)
    {
        try
        {
            Log.e("notice message",text);

            Intent aIntent = new Intent(Actions.NOTICE);

            aIntent.putExtra(Keys.MESSAGE,text);

            context.sendBroadcast(aIntent);
        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }
    }

    /**
     * 请求权限
     * @param context context
     */
    public static void requestPermission(Context context)
    {
        try
        {

            Log.e("HHDoctor","request permission");

            Intent aIntent = new Intent(Actions.PERMISSION);

            context.sendBroadcast(aIntent);
        }
        catch (Exception e)
        {
            Logger.e(e.toString());
        }
    }

    static class Actions
    {
        /**
         * 通知
         */
        static final String  NOTICE = "com.hhmedic.doctor.notify";

        /**
         * 申请权限通知
         */
        static final String  PERMISSION = "com.hhmedic.doctor.focus";

    }


    public static class Keys
    {
        static final String MESSAGE = "message";
    }


}
