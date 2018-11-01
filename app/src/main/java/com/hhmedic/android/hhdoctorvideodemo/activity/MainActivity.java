package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hhmedic.android.sdk.HHDoctor;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.sdk.listener.HHLoginListener;
import com.yanzhenjie.permission.AndPermission;

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

        findViewById(R.id.login_button).setOnClickListener(v -> login());

        findViewById(R.id.logout_button).setOnClickListener( v -> HHDoctor.loginOut(this));
    }

    private void login() {

        AndPermission.with(this).permission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        )
                .onGranted(permissions -> doLogin())

                .onDenied(permissions -> {


                    if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
                        // 这些权限被用户总是拒绝。
//                        alwaysTips(permissionTips());
                    }
                    else
                    {

                    }

                })
                .start();
    }

    private void doLogin() {
        long uuid = 100000470; //这个ID是和和缓对接之后得到的和缓的UUID
        HHDoctor.login(this,uuid, new HHLoginListener() {
            @Override
            public void onSuccess() {
                loginForward();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 跳转选择呼叫类型界面
     */
    private void loginForward() {
        Intent intent = new Intent(this,CallSelectorAct.class);
        startActivity(intent);
    }
}
