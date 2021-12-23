package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.listener.HHLoginListener;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    private EditText mUserTokenEdit;

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

        initActionBar(findViewById(R.id.toolbar));

        setTitle(R.string.login_button);

        findViewById(R.id.login_button).setOnClickListener(v -> login());
        mUserTokenEdit = findViewById(R.id.userToken);

        findViewById(R.id.is_in_develop).setVisibility(LocalConfig.isDevelop(this) ? View.VISIBLE : View.GONE);
        findViewById(R.id.use_default_toke).setOnClickListener(v -> mUserTokenEdit.setText(LocalConfig.DefaultUserToken));

        findViewById(R.id.login_third_button).setOnClickListener(view -> loginWithThirdId());
    }

    private void login() {
        doLogin();
    }

    private void doLogin() {
        String userToken = mUserTokenEdit.getText().toString().trim(); //这个ID是和和缓对接之后得到的和缓的UserToken
        if (userToken.isEmpty()) {
            Toast.makeText(this, "请输入需要登录的userToken", Toast.LENGTH_SHORT).show();
            return;
        }

        loginWithToken(userToken);
    }

    private void loginWithToken(String userToken) {
        LocalConfig.setLoginedToken(this, userToken);
        HHDoctor.login(this, userToken, new HHLoginListener() {
            @Override
            public void onSuccess() {
                loginForward();
            }

            @Override
            public void onError(String s) {
                Logger.e(s);
                Toast.makeText(MainActivity.this, "请确保参数使用环境，不要使用非正式环境参数访问正式环境" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loginWithThirdId() {

        String uid = ((EditText)findViewById(R.id.uid)).getText().toString().trim();
        String time = ((EditText)findViewById(R.id.time)).getText().toString().trim();
        String secret = ((EditText)findViewById(R.id.secret)).getText().toString().trim();
        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(time) || TextUtils.isEmpty(secret)) {
            Toast.makeText(this, "请输入所必须的参数再进行登录操作", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("time", time);
        hashMap.put("secret", secret);
        HHDoctor.loginForThirdId(this, hashMap, new HHLoginListener() {
            @Override
            public void onSuccess() {
                loginForward();
            }

            @Override
            public void onError(String tips) {
                Logger.e(tips);
                Toast.makeText(MainActivity.this, tips, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 跳转选择呼叫类型界面
     */
    private void loginForward() {
        Intent intent = new Intent(this, CallSelectorAct.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent settingIntent = new Intent(this, SettingAct.class);
                startActivity(settingIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
