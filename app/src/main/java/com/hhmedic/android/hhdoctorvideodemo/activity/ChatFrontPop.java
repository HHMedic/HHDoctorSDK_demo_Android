package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.sdk.module.video.hangup.HangUp;
import com.orhanobut.logger.Logger;

public class ChatFrontPop extends PopupWindow {


    public ChatFrontPop(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.hh_chat_front_layout,null);

        setContentView(view);

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        setFocusable(true);

        setOutsideTouchable(true);

        setBackgroundDrawable(new ColorDrawable(0));

        setClippingEnabled(false);

        view.findViewById(R.id.close).setOnClickListener(v -> {
            HangUp.doHangup(() -> {
                Logger.e("do hangup listener =============");
                Toast.makeText(context, "已经触发挂断回调", Toast.LENGTH_SHORT).show();
            });
            dismiss();
        });
    }
}
