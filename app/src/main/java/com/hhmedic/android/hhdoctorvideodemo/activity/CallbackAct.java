package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.hhdoctorvideodemo.application.Command;
import com.hhmedic.android.hhdoctorvideodemo.application.HHDemoUtils;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.listener.HHCallListener;
import com.hhmedic.android.sdk.module.video.avchat.sound.Refuse;

public class CallbackAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String command = getIntent().getStringExtra("command");
        doCommand(command);
    }

    @Override
    protected int contentViewId() {
        return R.layout.act_call_back;
    }

    private void doCommand(String command) {
        if (command == null || command.isEmpty()) {
            finish();
            return;
        }
        HHDemoUtils.requestPermission(this);
        switch (command) {
            case Command.REFUSE:

                doRefuse();

                break;

            case Command.ACCEPT:


                doAccept();


                break;


            case Command.DEPT_ADULT:

                doSelectDepart(false);

                break;

            case Command.DEPT_CHILD:

                doSelectDepart(true);

                break;

            default:

                break;
        }
        finish();
    }

    private void doRefuse() {
        HHDoctor.onRefuse(this, "此处填写服务端推送的数据", new Refuse.OnCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(String s) {
            }
        });
    }

    private void doAccept() {
        HHDoctor.onAccept(this, new HHCallListener() {
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
    }

    private void doSelectDepart(boolean callChild) {
        if (callChild) {
            HHDoctor.callForChild(this, new HHCallListener() {
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
        } else {
            HHDoctor.callForAdult(this, new HHCallListener() {
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
        }
    }
}
