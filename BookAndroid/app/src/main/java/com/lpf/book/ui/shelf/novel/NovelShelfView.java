package com.lpf.book.ui.shelf.novel;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class NovelShelfView extends BaseView<NovelShelfFragment> {
    private RecyclerView recycler;
    private LocalNovelListAdapter adapter;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        super.onViewCreated(base, savedInstanceState);
        recycler = get(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 2));
        adapter = new LocalNovelListAdapter((int) (base.getActivity().getWindowManager().getDefaultDisplay().getWidth() / 2.2));
        recycler.setAdapter(adapter);
    }

    public LocalNovelListAdapter getAdapter() {
        return adapter;
    }
}
