package com.lpf.book.ui.add.recom.book;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class RecomBookView extends BaseView<RecomBookActivity> {
    private RecomBookListAdapter adapter;
    private RecyclerView recycler;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        TextView title = get(R.id.title);
        title.setText("热门图书");
        base.click(R.id.back, () -> base.getActivity().finish());
        recycler = get(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(base.getContext()));
        adapter = new RecomBookListAdapter();
        recycler.setAdapter(adapter);
    }

    public RecomBookListAdapter getAdapter() {
        return adapter;
    }
}
