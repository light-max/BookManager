package com.lpf.book.ui.add.recom;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.ui.add.recom.book.RecomBookActivity;
import com.lpf.book.ui.add.recom.novel.RecomNovelActivity;

public class RecomView extends BaseView<RecomFragment> {
    private RecyclerView recycler;
    private RecomListAdapter adapter;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(base.getContext(), 3));
        adapter = new RecomListAdapter((int) (base.getActivity().getWindowManager().getDefaultDisplay().getWidth() / 3.3));
        recycler.setAdapter(adapter);
        click(R.id.book, () -> {
            base.getContext().startActivity(new Intent(base.getContext(), RecomBookActivity.class));
        });
        click(R.id.novel, () -> {
            base.getContext().startActivity(new Intent(base.getContext(), RecomNovelActivity.class));
        });
    }

    public RecomListAdapter getAdapter() {
        return adapter;
    }
}


