package com.lpf.book.ui.details.novel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class NovelDetailsActivity extends BaseActivity<NovelDetailsModel, NovelDetailsView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_novel_details;
    }

    public static void start(Context context, String name) {
        Intent intent = new Intent(context, NovelDetailsActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        String name = getIntent().getStringExtra("name");
        getModel().getNovelDetails(name);
        getModel().check(name);
        getView().checkNovelIsDownload(name);
        getModel().addReadNumber(name);
    }
}
