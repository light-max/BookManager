package com.lpf.book.ui.people.history;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class BorrowHistoryView extends BaseView<BorrowHistoryActivity> {
    private RecyclerView recycler;
    private BorrowHistoryListAdapter adapter;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
        recycler = get(R.id.recycler);
        adapter = new BorrowHistoryListAdapter();
        recycler.setAdapter(adapter);
    }

    public BorrowHistoryListAdapter getAdapter() {
        return adapter;
    }
}
