package com.lpf.book.ui.add.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.fragment.BaseFragment;
import com.lpf.book.ui.details.book.BookDetailsActivity;

public class BookFragment extends BaseFragment<BookModel, BookView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchValueData.getInstance().postValue(new SearchValueData.Value());
        getV().getAdapter().setOnItemClickListener((data, position) -> {
            BookDetailsActivity.start(requireContext(), data.getId());
        });
    }
}
