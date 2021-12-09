package com.lpf.book.ui.people.uinfo;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class UserInfoActivity extends BaseActivity<UserInfoModel, UserInfoView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getModel().getAccountTypeInfo();
    }
}
