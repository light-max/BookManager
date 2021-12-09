package com.lpf.book.ui.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class LoginActivity extends BaseActivity<LoginModel, LoginView> {
    public static final String auto = "auto";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        view.getPost().setOnClickListener(v -> {
            model.login(view.isStudent(), view.getUsername(), view.getPassword());
        });
        boolean auto = getIntent().getBooleanExtra(LoginActivity.auto, false);
        if (auto && view.isAuto()) {
            view.getPost().callOnClick();
        }
    }
}
