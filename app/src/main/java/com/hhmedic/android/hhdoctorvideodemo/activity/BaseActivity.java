package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class BaseActivity extends AppCompatActivity {

    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (!useDataBinding() && contentViewId() != 0) {
            setContentView(contentViewId());
        }
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        QMUIStatusBarHelper.translucent(this);
        initUI();
    }

    protected int contentViewId() {
        return 0;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initUI();
    }

    protected void initUI() {

    }

    protected boolean useDataBinding() {
        return false;
    }

    protected void initActionBar(Toolbar toolbar)
    {
        if (toolbar == null)
        {
            return;
        }

        setSupportActionBar(toolbar);

        initActionBar();
    }

    public void initActionBar()
    {
        if(getSupportActionBar() ==null)
        {
            return ;
        }

        // 以下代码用于去除阴影
        if(Build.VERSION.SDK_INT >= 21)
        {
            getSupportActionBar().setElevation(0);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }

        return false;
    }
}
