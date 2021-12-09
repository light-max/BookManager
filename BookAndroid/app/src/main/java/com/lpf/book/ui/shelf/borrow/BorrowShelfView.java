package com.lpf.book.ui.shelf.borrow;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.data.result.BorrowDetails;

import java.util.List;

public class BorrowShelfView extends BaseView<BorrowShelfFragment> {
    private RecyclerView recycler;
    private BorrowedBookAdapter adapter;
    private TextView empty;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        empty = get(R.id.empty);
        adapter = new BorrowedBookAdapter();
        recycler.setAdapter(adapter);
        SwipeRefreshLayout refreshLayout = get(R.id.swipe);
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(false);
            this.base.getModel().getBorrowedBook();
        });
    }

    public void setData(List<BorrowDetails> data) {
        adapter.setNewData(data);
        recycler.setVisibility(data.isEmpty() ? View.GONE : View.VISIBLE);
        empty.setVisibility(data.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
