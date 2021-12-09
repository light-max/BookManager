package com.lpf.book.ui.add.novel;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.view.SearchView;

public class NovelView extends BaseView<NovelFragment> {
    private RecyclerView recycler;
    private NovelListAdapter adapter;
    private SearchView searchView;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        searchView = get(R.id.search);
        recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 2));
        adapter = new NovelListAdapter((int) (base.getActivity().getWindowManager().getDefaultDisplay().getWidth() / 2.2));
        recycler.setAdapter(adapter);
    }

    public NovelListAdapter getAdapter() {
        return adapter;
    }

    public SearchView getSearchView() {
        return searchView;
    }
}
