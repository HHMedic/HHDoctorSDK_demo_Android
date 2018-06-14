package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.hhdoctorvideodemo.application.HHDemoUtils;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.listener.HHCallListener;

public class CallSelectorAct extends BaseActivity implements View.OnClickListener{

    private boolean noticeTTS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentViewId() {
        return R.layout.act_call_selector;
    }

    @Override
    protected void initUI() {
        super.initUI();
        findViewById(R.id.all_btn).setOnClickListener(this);
        findViewById(R.id.child_btn).setOnClickListener(this);
        findViewById(R.id.back_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_btn:
                callAdult();
                break;
            case  R.id.child_btn:
                callChild();
                break;
            case R.id.back_btn:
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        HHDemoUtils.notice(this,getString(R.string.hp_notice_depart));
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!noticeTTS) {
            HHDemoUtils.notice(this,getString(R.string.hp_notice_depart));
        }

        noticeTTS = true;

    }

    /**
     * 呼叫成人医生
     */
    private void callAdult() {

        HHDoctor.callForAdult(this, new HHCallListener() {

            @Override
            public void onStart() {
                Log.i(TAG,"call onStart");
            }

            @Override
            public void onCalling() {
                Log.i(TAG,"call onCalling");
            }

            @Override
            public void onInTheCall() {
                Log.i(TAG,"call onInTheCall");
            }

            @Override
            public void onFinish() {
                Log.i(TAG,"call onFinish");
            }

            @Override
            public void onCallSuccess() {
                Log.i(TAG,"call onCallSuccess");
            }

            @Override
            public void onFail(int i) {
                Log.i(TAG,"call onFail");
            }

            @Override
            public void onCancel() {
                Log.i(TAG,"call onCancel");
            }

            @Override
            public void onLineUpTimeout() {
                Log.i(TAG,"call onLineUpTimeout");
            }

            @Override
            public void onLineUp() {
                Log.i(TAG,"call onLineUp");
            }
        });
    }

    /**
     * 呼叫儿童医生
     */
    private void callChild() {
        HHDoctor.callForChild(this, new HHCallListener() {

            @Override
            public void onStart() {
                Log.i(TAG,"call onStart");
            }

            @Override
            public void onCalling() {
                Log.i(TAG,"call onCalling");
            }

            @Override
            public void onInTheCall() {
                Log.i(TAG,"call onInTheCall");
            }

            @Override
            public void onFinish() {
                Log.i(TAG,"call onFinish");
            }

            @Override
            public void onCallSuccess() {
                Log.i(TAG,"call onCallSuccess");
            }

            @Override
            public void onFail(int i) {
                Log.i(TAG,"call onFail");
            }

            @Override
            public void onCancel() {
                Log.i(TAG,"call onCancel");
            }

            @Override
            public void onLineUpTimeout() {
                Log.i(TAG,"call onLineUpTimeout");
            }

            @Override
            public void onLineUp() {
                Log.i(TAG,"call onLineUp");
            }
        });
    }
}
