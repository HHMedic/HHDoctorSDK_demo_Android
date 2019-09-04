package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.hhdoctorvideodemo.application.HHDemoUtils;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.listener.HHCallListener;

public class CallSelectorAct extends BaseActivity implements View.OnClickListener{

    private boolean noticeTTS;
    private EditText mOrderIdEdit;
//    private final String userToken = "ECEEDCCD74B7D54BCF6690B7E26262B73F0D04F68EA2608F6783B874E4F50EEF";

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
        findViewById(R.id.is_in_develop).setVisibility(LocalConfig.isDevelop(this) ? View.VISIBLE : View.GONE);
        findViewById(R.id.all_btn).setOnClickListener(this);
        findViewById(R.id.child_btn).setOnClickListener(this);
        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.view_list).setOnClickListener(this);
        findViewById(R.id.view_detail).setOnClickListener(this);
        findViewById(R.id.medicine_demo).setOnClickListener(this);
        findViewById(R.id.view_all).setOnClickListener(this);
        mOrderIdEdit = findViewById(R.id.orderId);
        mOrderIdEdit.setText(LocalConfig.DefaultCallOrderId);
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
            case R.id.view_all:
                viewAll();
                break;
            case R.id.view_list:
                viewList();
                break;
            case R.id.view_detail:
                viewDetail();
                break;
            case R.id.back_btn:
                HHDoctor.loginOut(this);
                LocalConfig.setLoginedToken(this, "");
                finish();
                break;
            case R.id.medicine_demo:
                medicineDemo();
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
            public void onStart(String orderId) {
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
            public void onStart(String orderId) {
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
     * 查看所有成员病历存档列表
     */
    private void viewAll() {
        Intent intent = new Intent(this, ViewDetailAct.class);
        String url = HHDoctor.getAllMedics(this, LocalConfig.getLoginedToken(this));
        Log.e("list url", url);
        intent.putExtra("url", url);
        intent.putExtra("title", "病历存档列表");
        startActivity(intent);
    }

    /**
     * 查看病历存档列表
     */
    private void viewList() {
        Intent intent = new Intent(this, ViewDetailAct.class);
        String url = HHDoctor.getMedicListUrl(this, LocalConfig.getLoginedToken(this));
        Log.e("list url", url);
        intent.putExtra("url", url);
        intent.putExtra("title", "病历存档列表");
        startActivity(intent);
    }

    /**
     * 查看病历存档详情
     */
    private void viewDetail() {
        String orderId = mOrderIdEdit.getText().toString();
        if (orderId.isEmpty()) {
            Toast.makeText(this, "请提供病历订单号", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, ViewDetailAct.class);
        String url = HHDoctor.getMedicDetailUrl(this, LocalConfig.getLoginedToken(this), orderId);
        intent.putExtra("url", url);
        intent.putExtra("title", "病历存档详情");
        startActivity(intent);
    }

    private void medicineDemo() {
        Intent intent = new Intent(this, MedicineDemo.class);
        startActivity(intent);
    }
}
