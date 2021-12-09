package com.lpf.book.ui.people.download;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class DownloadActivity extends BaseActivity<DownloadModel, DownloadView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
    }
}
