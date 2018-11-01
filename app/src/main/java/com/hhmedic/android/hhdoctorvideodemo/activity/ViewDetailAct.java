package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.sdk.HHDoctor;

import static android.view.KeyEvent.KEYCODE_BACK;

public class ViewDetailAct extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        initUI(getIntent());
    }

    private void initUI(Intent intent) {
        findViewById(R.id.back_btn).setOnClickListener(v -> {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return;
            }
            finish();
        });
        TextView textView = findViewById(R.id.title);
        textView.setText(intent.getStringExtra("title"));
        mWebView = findViewById(R.id.webView);
        configWeb(mWebView);
        mWebView.loadUrl(intent.getStringExtra("url"));
    }

    private void configWeb(WebView webView)
    {
        if (webView == null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
            return;
        }

        WebSettings settings = webView.getSettings();

        settings.setAppCacheEnabled(true);

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        settings.setJavaScriptEnabled(true);

        settings.setLoadWithOverviewMode(true);

        settings.setUseWideViewPort(true);

        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);

        settings.setSupportZoom(true);

        settings.setUseWideViewPort(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {

        }
        super.onDestroy();
    }
}
