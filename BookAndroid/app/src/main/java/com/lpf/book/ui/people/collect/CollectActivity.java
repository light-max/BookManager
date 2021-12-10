package com.lpf.book.ui.people.collect;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class CollectActivity extends BaseActivity<CollectModel, CollectView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getModel().getNovelCollect();
    }
}
