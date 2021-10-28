package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.hhdoctorvideodemo.application.HHDemoUtils;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.Location;
import com.hhmedic.android.sdk.listener.HHCallListener;
import com.hhmedic.android.sdk.module.video.hangup.HangUp;
import com.orhanobut.logger.Logger;

public class CallSelectorAct extends BaseActivity implements View.OnClickListener{

    private boolean noticeTTS;
//    private EditText mOrderIdEdit;
    private ChatFrontPop mPop;
//    private Timer mTimer;

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

        initActionBar(findViewById(R.id.toolbar));

        setTitle(R.string.hp_sdk_demo_main);

        findViewById(R.id.is_in_develop).setVisibility(LocalConfig.isDevelop(this) ? View.VISIBLE : View.GONE);
//        findViewById(R.id.multi_video).setOnClickListener(this);
//        findViewById(R.id.all_btn).setOnClickListener(this);
//        findViewById(R.id.child_btn).setOnClickListener(this);
//        findViewById(R.id.back_btn).setOnClickListener(this);
//        findViewById(R.id.view_list).setOnClickListener(this);
//        findViewById(R.id.view_detail).setOnClickListener(this);
//        findViewById(R.id.view_all).setOnClickListener(this);
        findViewById(R.id.message).setOnClickListener(this);
        findViewById(R.id.call).setOnClickListener(this);
//        mOrderIdEdit = findViewById(R.id.orderId);
//        mOrderIdEdit.setText(LocalConfig.DefaultCallOrderId);

        findViewById(R.id.set_location).setOnClickListener(v -> setLocation());
//        findViewById(R.id.hang_up).setOnClickListener(v -> {
//            HangUp.doHangup(() -> {
//                Logger.e("do hangup listener =============");
//                Toast.makeText(this, "已经触发挂断回调", Toast.LENGTH_SHORT).show();
//            });
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.all_btn:
//                callAdult();
//                break;
//            case  R.id.child_btn:
//                callChild();
//                break;
//            case R.id.view_all:
//                viewAll();
//                break;
//            case R.id.view_list:
//                viewList();
//                break;
//            case R.id.view_detail:
//                viewDetail();
//                break;
//            case R.id.back_btn:
//                HHDoctor.logOut(this);
//                LocalConfig.setLoginedToken(this, "");
//                finish();
//                break;
            case R.id.message:
                forwardMessage();
                break;
            case R.id.call:
                selectCall();
                break;
//            case R.id.multi_video:
//                forwardMultiVideo();
//                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.call_selector_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
//    private void callAdult() {
//
////        CallExtensionSetting.setJsonExtMessage("callAdult");
//
//        HHDoctor.callForAdult(this, new HHCallExListener() {
//
//            @Override
//            public void onLoadDoctor(HHCallInfo callInfo) {
//
//            }
//
//            @Override
//            public void onDoctorAgree() {
//
////                testFrontView();
//            }
//
//            @Override
//            public void onCallError(String error) {
//
//            }
//
//            @Override
//            public void onStart(String orderId) {
//                Log.i(TAG,"call onStart");
//            }
//
//            @Override
//            public void onCalling() {
//                Log.i(TAG,"call onCalling");
//            }
//
//            @Override
//            public void onInTheCall() {
//                Log.i(TAG,"call onInTheCall");
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG,"call onFinish");
//            }
//
//            @Override
//            public void onCallSuccess() {
//                Log.i(TAG,"call onCallSuccess");
//            }
//
//            @Override
//            public void onFail(int i) {
//                Log.i(TAG,"call onFail");
//            }
//
//            @Override
//            public void onCancel() {
//                Log.i(TAG,"call onCancel");
//            }
//
//            @Override
//            public void onLineUpTimeout() {
//                Log.i(TAG,"call onLineUpTimeout");
//            }
//
//            @Override
//            public void onLineUp() {
//                Log.i(TAG,"call onLineUp");
//            }
//        });
//    }

    /**
     * 呼叫儿童医生
     */
//    private void callChild() {
////        CallExtensionSetting.setJsonExtMessage("callChild");
//        HHDoctor.callForChild(this, new HHCallListener() {
//
//            @Override
//            public void onStart(String orderId) {
//                Log.i(TAG,"call onStart");
//            }
//
//            @Override
//            public void onCalling() {
//                Log.i(TAG,"call onCalling");
//            }
//
//            @Override
//            public void onInTheCall() {
//                Log.i(TAG,"call onInTheCall");
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG,"call onFinish");
//            }
//
//            @Override
//            public void onCallSuccess() {
//                Log.i(TAG,"call onCallSuccess");
//            }
//
//            @Override
//            public void onFail(int i) {
//                Log.i(TAG,"call onFail");
//            }
//
//            @Override
//            public void onCancel() {
//                Log.i(TAG,"call onCancel");
//            }
//
//            @Override
//            public void onLineUpTimeout() {
//                Log.i(TAG,"call onLineUpTimeout");
//            }
//
//            @Override
//            public void onLineUp() {
//                Log.i(TAG,"call onLineUp");
//            }
//        });
//    }

//    private void forwardMultiVideo() {
//        Intent intent = new Intent(this, MultiVideoAct.class);
//        startActivity(intent);
//    }

    /**
     * 查看所有成员病历存档列表
     */
//    private void viewAll() {
//        Intent intent = new Intent(this, ViewDetailAct.class);
//        String url = HHDoctor.getAllMedics(this, LocalConfig.getLoginedToken(this));
//        Log.e("list url", url);
//        intent.putExtra("url", url);
//        intent.putExtra("title", "病历存档列表");
//        startActivity(intent);
//    }

    /**
     * 查看病历存档列表
     */
//    private void viewList() {
//        Intent intent = new Intent(this, ViewDetailAct.class);
//        String url = HHDoctor.getMedicListUrl(this, LocalConfig.getLoginedToken(this));
//        Log.e("list url", url);
//        intent.putExtra("url", url);
//        intent.putExtra("title", "病历存档列表");
//        startActivity(intent);
//    }

    /**
     * 查看病历存档详情
     */
//    private void viewDetail() {
//        String orderId = mOrderIdEdit.getText().toString();
//        if (orderId.isEmpty()) {
//            Toast.makeText(this, "请提供病历订单号", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Intent intent = new Intent(this, ViewDetailAct.class);
//        String url = HHDoctor.getMedicDetailUrl(this, LocalConfig.getLoginedToken(this), orderId);
//        intent.putExtra("url", url);
//        intent.putExtra("title", "病历存档详情");
//        startActivity(intent);
//    }

    private void forwardMessage() {
        HHDoctor.message(this);
    }

    private void selectCall() {

//        CallExtensionSetting.setNormalExtMessage("selectCall");

        HHDoctor.call(this, new HHCallListener() {
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
                removeTestFrontView();
            }

            @Override
            public void onCallSuccess() {

            }

            @Override
            public void onFail(int code) {
                removeTestFrontView();
            }

            @Override
            public void onCancel() {
                removeTestFrontView();
            }

            @Override
            public void onLineUpTimeout() {

            }

            @Override
            public void onLineUp() {

            }
        });
    }

    /**
     * 使用购药功能需要设置经纬度
     */
    private void setLocation() {
        double lng = 0.0;
        double lat = 0.0;
        try {
            lng = Double.parseDouble(((EditText)findViewById(R.id.lng)).getText().toString());
            lat = Double.parseDouble(((EditText)findViewById(R.id.lat)).getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Location.sendLocation(this,lng,lat);
        Toast.makeText(this, "经纬度已经发送", Toast.LENGTH_SHORT).show();
    }


//    private void testFrontView() {
//        ChatFrontPop pop = new ChatFrontPop(this);
//        View parent = HHDoctor.getChatParentView();
//        pop.showAtLocation(parent, Gravity.NO_GRAVITY,0,0);
//        mPop = pop;
//    }

    private void removeTestFrontView() {
        if (mPop != null) {
            mPop.dismiss();
            mPop = null;
        }
    }

    private void logout() {
        HHDoctor.logOut(this);
        LocalConfig.setLoginedToken(this, "");
        finish();
    }
}
