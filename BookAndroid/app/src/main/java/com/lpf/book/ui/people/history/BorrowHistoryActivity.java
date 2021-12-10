package com.lpf.book.ui.people.history;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;

public class BorrowHistoryActivity extends BaseActivity<BorrowHistoryModel, BorrowHistoryView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_borrow_history;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getModel().getBorrowHistory();
    }
}
