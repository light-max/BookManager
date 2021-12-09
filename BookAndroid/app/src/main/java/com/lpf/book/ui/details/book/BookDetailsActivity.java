package com.lpf.book.ui.details.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class BookDetailsActivity extends BaseActivity<BookDetailsModel, BookDetailsView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_details;
    }

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getModel().getBookDetails(getIntent().getIntExtra("id", 0));
    }
}
