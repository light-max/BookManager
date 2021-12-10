package com.lpf.book.ui.people.collect;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class CollectView extends BaseView<CollectActivity> {
    private RecyclerView recycler;
    private CollectListAdapter adapter;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
        recycler = get(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(this.base, 2));
        adapter = new CollectListAdapter((int) (base.getActivity().getWindowManager().getDefaultDisplay().getWidth() / 2.2));
        recycler.setAdapter(adapter);
    }

    public CollectListAdapter getAdapter() {
        return adapter;
    }
}
