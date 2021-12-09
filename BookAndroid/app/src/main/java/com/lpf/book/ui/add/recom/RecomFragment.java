package com.lpf.book.ui.add.recom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.fragment.BaseFragment;
import com.lpf.book.ui.details.book.BookDetailsActivity;
import com.lpf.book.ui.details.novel.NovelDetailsActivity;

public class RecomFragment extends BaseFragment<RecomModel, RecomView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getModel().getRandRecom();
        getV().getAdapter().setOnItemClickListener((data, position) -> {
            if (data.isBook()) {
                BookDetailsActivity.start(requireContext(), data.getId());
            } else {
                NovelDetailsActivity.start(requireContext(), data.getName());
            }
        });
    }
}
