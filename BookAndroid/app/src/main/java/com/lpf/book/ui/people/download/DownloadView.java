package com.lpf.book.ui.people.download;

import android.os.Bundle;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class DownloadView extends BaseView<DownloadActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
    }
}
