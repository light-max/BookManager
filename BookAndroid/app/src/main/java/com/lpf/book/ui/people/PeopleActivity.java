package com.lpf.book.ui.people;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class PeopleActivity extends BaseActivity<PeopleModel, PeopleView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_people;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
    }
}
