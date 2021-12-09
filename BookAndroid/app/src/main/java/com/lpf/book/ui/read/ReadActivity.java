package com.lpf.book.ui.read;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;
import com.lpf.book.novel.parser.NovelParser;

public class ReadActivity extends BaseActivity<ReadModel, ReadView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
    }

    public static void start(Class<? extends NovelParser> parser, Context context, String name) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra("parser", parser);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
}
