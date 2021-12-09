package com.lpf.book.ui.add.recom.novel;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;
import com.lpf.book.ui.details.novel.NovelDetailsActivity;

public class RecomNovelActivity extends BaseActivity<RecomNovelModel, RecomNovelView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recom_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getModel().getRecomNovel();
        getView().getAdapter().setOnItemClickListener((data, position) -> {
            NovelDetailsActivity.start(this, data.getName());
        });
    }
}
