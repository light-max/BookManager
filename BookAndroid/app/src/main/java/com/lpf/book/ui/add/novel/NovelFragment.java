package com.lpf.book.ui.add.novel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.fragment.BaseFragment;
import com.lpf.book.ui.details.novel.NovelDetailsActivity;

public class NovelFragment extends BaseFragment<NovelModel, NovelView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_novel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model.getAllNovel(null);
        this.view.getSearchView().setOnSearchListener(text -> {
            model.getAllNovel(text);
        });
        this.view.getSearchView().setOnResetListener(() -> {
            model.getAllNovel(null);
        });
        getV().getAdapter().setOnItemClickListener((data, position) -> {
            NovelDetailsActivity.start(requireContext(), data.getName());
        });
    }
}
