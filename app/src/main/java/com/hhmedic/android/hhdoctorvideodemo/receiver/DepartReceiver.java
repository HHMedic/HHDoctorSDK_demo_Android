package com.hhmedic.android.hhdoctorvideodemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.hhmedic.android.hhdoctorvideodemo.application.Command;
import com.hhmedic.android.hhdoctorvideodemo.application.HHDemoUtils;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.listener.HHCallListener;

/**
 * Created by iOS on 2018/3/20.
 *
 *
 */

public class DepartReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {

        String command = intent.getStringExtra("command");

        if (!TextUtils.isEmpty(command))
        {
            switch (command) {
                case Command.DEPT_ADULT:
                    HHDoctor.callForAdult(context, new HHCallListener() {
                        @Override
                        public void onStart(String orderId) {

                        }

                        @Override
                        public void onCalling() {

                        }

                        @Override
                        public void onInTheCall() {

                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onCallSuccess() {

                        }

                        @Override
                        public void onFail(int i) {

                        }

                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onLineUpTimeout() {

                        }

                        @Override
                        public void onLineUp() {

                        }
                    });
                    break;
                case Command.DEPT_CHILD:
                    HHDoctor.callForChild(context, new HHCallListener() {
                        @Override
                        public void onStart(String orderId) {

                        }

                        @Override
                        public void onCalling() {

                        }

                        @Override
                        public void onInTheCall() {

                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onCallSuccess() {

                        }

                        @Override
                        public void onFail(int i) {

                        }

                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onLineUpTimeout() {

                        }

                        @Override
                        public void onLineUp() {

                        }
                    });
                    break;
                    default:
                        break;
            }
        }
    }
}
