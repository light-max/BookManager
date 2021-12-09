package com.lpf.book.ui.add.book;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;

public class BookView extends BaseView<BookFragment> {
    private RecyclerView recycler;
    private BookListAdapter adapter;
    private CardView filter;

    private View filterView;
    private AlertDialog dialog;

    @Override
    public void onViewCreated(Base base, Bundle savedInstanceState) {
        recycler = get(R.id.recycler);
        filter = get(R.id.filter);
        adapter = new BookListAdapter();
        recycler.setAdapter(adapter);
        filterView = View.inflate(base.getContext(), R.layout.view_book_filter, null);
        filter.setOnClickListener(v -> showDialog());

        filterView.findViewById(R.id.post).setOnClickListener(v -> {
            dialog.dismiss();
            EditText name = filterView.findViewById(R.id.name);
            EditText author = filterView.findViewById(R.id.author);
            EditText des = filterView.findViewById(R.id.des);
            SearchValueData.getInstance().postValue(new SearchValueData.Value(
                    name.getText().toString(),
                    author.getText().toString(),
                    des.getText().toString()
            ));
        });
    }

    public BookListAdapter getAdapter() {
        return adapter;
    }

    public void showDialog() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(base.requireContext())
                    .setView(filterView)
                    .show();
        } else {
            dialog.show();
        }
    }
}
