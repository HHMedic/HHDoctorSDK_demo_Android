package com.hhmedic.android.hhdoctorvideodemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhmedic.android.hhdoctorvideodemo.R;
import com.hhmedic.android.hhdoctorvideodemo.databinding.ActivityMultiVideoBinding;
import com.hhmedic.android.sdk.HHDoctor;
import com.hhmedic.android.sdk.module.common.CallType;
import com.hhmedic.android.sdk.module.video.multi.data.HHInviteUser;

public class MultiVideoAct extends BaseActivity {

    public ActivityMultiVideoBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_multi_video);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected boolean useDataBinding() {
        return true;
    }

    @Override
    protected void initUI() {
        super.initUI();
        initActionBar(mBinding.toolbar);
        mBinding.startCall.setOnClickListener( view -> startCall());
    }

    private void startCall() {
        String userToken = mBinding.editUserToken.getText().toString();
        String userName = mBinding.editUserName.getText().toString();
        String userPhoto = mBinding.editUserPhoto.getText().toString();
        HHInviteUser inviteUser = new HHInviteUser(userToken);
        inviteUser.setNickName(userName);
        inviteUser.setPhotoUrl(userPhoto);
        HHDoctor.multiCall(this, CallType.all,inviteUser);
    }
}
