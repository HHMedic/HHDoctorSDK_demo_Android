package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.DataBindingUtil;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.hhdoctorvideodemo.application.DoctorApplication;
import com.hhmedic.android.hhdoctorvideodemo.databinding.ActivitySettingLayoutBinding;
import com.hhmedic.android.sdk.HHDoctor;

public class SettingAct extends BaseActivity {

    private ActivitySettingLayoutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {
        super.initUI();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting_layout);

        initActionBar(mBinding.toolbarLayout.toolbar);

        setTitle(R.string.activity_setting_title);

        SwitchCompat mIsDevelopSwitch = mBinding.developSwitch;
        mIsDevelopSwitch.setChecked(LocalConfig.isDevelop(this));
        mIsDevelopSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setDevelop(this, isChecked);
            switchReload();
        });

        SwitchCompat mCanAddSwitch = mBinding.canAddMember;
        mCanAddSwitch.setChecked(LocalConfig.getEnableAddMember(this));
        mCanAddSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableAddMember(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableMultiCallSwitch = mBinding.enableMultiCall;
        mEnableMultiCallSwitch.setChecked(LocalConfig.getEnableMultiCall(this));
        mEnableMultiCallSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableMultiCall(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableActivate = mBinding.enableActivate;
        mEnableActivate.setChecked(LocalConfig.getEnableActivate(this));
        mEnableActivate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableActivate(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableMedical = mBinding.enableMedical;
        mEnableMedical.setChecked(LocalConfig.getEnableMedical(this));
        mEnableMedical.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableMedical(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableUserCenter = mBinding.enableUserCenter;
        mEnableUserCenter.setChecked(LocalConfig.getEnableUserCenter(this));
        mEnableUserCenter.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableUserCenter(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableSummaryCard = mBinding.enableSummaryCard;
        mEnableSummaryCard.setChecked(LocalConfig.getEnableSummaryCard(this));
        mEnableSummaryCard.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableSummaryCard(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableMedicalCard = mBinding.enableMedicalCard;
        mEnableMedicalCard.setChecked(LocalConfig.getEnableMedicalCard(this));
        mEnableMedicalCard.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableMedicalCard(this, isChecked);
            switchReload();
        });



        SwitchCompat mEnableVipInfo = mBinding.enableVipInfo;
        mEnableVipInfo.setChecked(LocalConfig.getEnableVipInfo(this));
        mEnableVipInfo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableVipInfo(this, isChecked);
            switchReload();
        });


        SwitchCompat mEnableAddMemberInDoc = mBinding.canAddMemberInDoc;
        mEnableAddMemberInDoc.setChecked(LocalConfig.getEnableAddMemberInDoc(this));
        mEnableAddMemberInDoc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableAddMemberInDoc(this, isChecked);
            switchReload();
        });

        SwitchCompat mEnableCanBuy = mBinding.canBuy;
        mEnableCanBuy.setChecked(LocalConfig.getEnableCanBuy(this));
        mEnableCanBuy.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setEnableCanBuy(this, isChecked);
            switchReload();
        });

        SwitchCompat mHideCameraControl = mBinding.hideCameraControl;
        mHideCameraControl.setChecked(LocalConfig.getHideCameraControl(this));
        mHideCameraControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setHideCameraControl(this, isChecked);
            switchReload();
        });

        SwitchCompat mCloseCameraCall = mBinding.closeCameraCall;
        mCloseCameraCall.setChecked(LocalConfig.getCloseCameraCall(this));
        mCloseCameraCall.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setCloseCameraCall(this, isChecked);
            switchReload();
        });

        SwitchCompat mCloseMoreFunc = mBinding.closeMoreFunc;
        mCloseMoreFunc.setChecked(LocalConfig.getCloseMoreFunc(this));
        mCloseMoreFunc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            LocalConfig.setCloseMoreFunc(this, isChecked);
            switchReload();
        });

        findViewById(R.id.is_in_develop).setVisibility(LocalConfig.isDevelop(this) ? View.VISIBLE : View.GONE);

        mBinding.pid.setText(LocalConfig.getPid(this));

        mBinding.buttonSetPid.setOnClickListener(v -> {
            String pid = mBinding.pid.getText().toString();
            if (TextUtils.isEmpty(pid)) {
                Toast.makeText(SettingAct.this, "请填写需要设置的PID", Toast.LENGTH_SHORT).show();
                return;
            }
            LocalConfig.setPid(this, pid);
            switchReload();
        });

        String message_title = LocalConfig.getMessageTitle(this);
        if (!TextUtils.isEmpty(message_title)) {
            mBinding.messageTitle.setText(message_title);
        }
        mBinding.buttonSetMessageTitle.setOnClickListener(v -> {
            String title = mBinding.messageTitle.getText().toString();
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(SettingAct.this, "请填写需要设置的Message 界面的Title", Toast.LENGTH_SHORT).show();
                return;
            }
            LocalConfig.setMessageTitle(this, title);
            switchReload();
        });

        mBinding.buttonSetExtMessage.setOnClickListener(v -> {
            String message = mBinding.extMessage.getText().toString();
            if (TextUtils.isEmpty(message)) {
                Toast.makeText(SettingAct.this, "请填写需要设置的呼叫传入的附加信息", Toast.LENGTH_SHORT).show();
                return;
            }
            HHDoctor.setExtension(message);
            Toast.makeText(SettingAct.this, "设置完成", Toast.LENGTH_SHORT).show();
        });
    }

    private void switchReload() {
//        Toast.makeText(SettingAct.this, "切换设置后需要重启打开APP才会生效", Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(() -> System.exit(0), 1000);
        DoctorApplication.initSDK(getApplicationContext());
        Toast.makeText(SettingAct.this, "设置参数已经生效", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean useDataBinding() {
        return true;
    }
}