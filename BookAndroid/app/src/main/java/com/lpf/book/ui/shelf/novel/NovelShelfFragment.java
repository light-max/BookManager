package com.lpf.book.ui.shelf.novel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.fragment.BaseFragment;
import com.lpf.book.novel.parser.LocalNovelParser;
import com.lpf.book.ui.read.ReadActivity;

public class NovelShelfFragment extends BaseFragment<NovelShelfModel, NovelShelfView> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_novel_shelf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getV().getAdapter().setOnItemClickListener((data, position) -> {
            ReadActivity.start(LocalNovelParser.class, requireActivity(), data.getName());
        });
    }
}
