package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hhmedic.android.sdk.HHDoctor;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.sdk.listener.HHLoginListener;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int contentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        super.initUI();
        long uuid = 0; //这个ID是和和缓对接之后得到的和缓的UUID
        findViewById(R.id.login_button).setOnClickListener(v -> HHDoctor.login(this,uuid, new HHLoginListener() {
            @Override
            public void onSuccess() {
                loginForward();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }));

        findViewById(R.id.logout_button).setOnClickListener( v -> HHDoctor.loginOut(this));
    }

    /**
     * 跳转选择呼叫类型界面
     */
    private void loginForward() {
        Intent intent = new Intent(this,CallSelectorAct.class);
        startActivity(intent);
    }
}
