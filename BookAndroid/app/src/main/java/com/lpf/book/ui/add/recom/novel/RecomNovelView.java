package com.lpf.book.ui.add.recom.novel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class RecomNovelView extends BaseView<RecomNovelActivity> {
    private RecyclerView recycler;
    private RecomNovelListAdapter adapter;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        TextView title = get(R.id.title);
        title.setText("热门小说");
        base.click(R.id.back, () -> base.getActivity().finish());
        recycler = get(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 2));
        adapter = new RecomNovelListAdapter();
        recycler.setAdapter(adapter);
    }

    public RecomNovelListAdapter getAdapter() {
        return adapter;
    }
}
