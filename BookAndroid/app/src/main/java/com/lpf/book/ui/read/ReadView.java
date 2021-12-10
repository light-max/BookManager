package com.lpf.book.ui.read;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.lpf.book.R;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.utils.ChapterControllerView;
import com.lpf.book.utils.PageFlipTextView;

public class ReadView extends BaseView<ReadActivity> {
    private PageFlipTextView content;
    private TextView title;
    private TextView page;
    private ChapterControllerView controller;
    private OnChapterChangeListener onChapterChangeListener;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        content = get(R.id.content);
        title = get(R.id.mini_title);
        page = get(R.id.page_number);
        controller = get(R.id.controller);
        controller.setTitleText(this.base.getIntent().getStringExtra("name"));
        content.setOnPageNumberChangeListener((count, index) -> {
            page.setText((index + 1) + "/" + count);
        });
        content.setListener((view, action) -> {
            switch (action) {
                case PageFlipTextView.ACTION_LEFT_PAGE:
                    if (onChapterChangeListener != null) {
                        onChapterChangeListener.onChange(true);
                    }
                    break;
                case PageFlipTextView.ACTION_RIGHT_PAGE:
                    if (onChapterChangeListener != null) {
                        onChapterChangeListener.onChange(false);
                    }
                    break;
                case PageFlipTextView.ACTION_CLICK_CENTER:
                    controller.toggleVisible();
                    break;
            }
        });
        ReadModel model = this.base.getModel();
        controller.setOnActionListener(new ChapterControllerView.OnActionListener() {
            @Override
            public void onPreviousChapter() {
                if (onChapterChangeListener != null) {
                    onChapterChangeListener.onChange(true);
                }
            }

            @Override
            public void onNextChapter() {
                if (onChapterChangeListener != null) {
                    onChapterChangeListener.onChange(false);
                }
            }

            @Override
            public void onSetTextColor(int color) {
                content.setTextColor(color);
                controller.setTextColor(color);
                title.setTextColor(color);
                page.setTextColor(color);
            }

            @Override
            public void onSetBackgroundColor(int color) {
                get(R.id.root).setBackgroundColor(color);
                controller.setWidgetBackgroundColor(color);
                double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
                boolean dark = darkness > 0.5;
                base.map("dark", dark);
                ((ReadActivity) base.getActivity()).setStatusBar(dark);
            }

            @Override
            public void onSetFontSize(float size) {
                content.setTextSize(size);
            }

            @Override
            public void onBack() {
                base.getActivity().finish();
            }

            @Override
            public void onOpenList() {

            }

            @Override
            public void onSeekChapter(boolean checked, int index) {
                if (onChapterChangeListener != null) {
                    if (checked) {
                        onChapterChangeListener.setByPosition(index);
                    } else {
                        onChapterChangeListener.findTitle(index);
                    }
                }
            }
        });
    }

    public PageFlipTextView getContent() {
        return content;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getPage() {
        return page;
    }

    public void setMaxChapterNumber(int count) {
        controller.setProgressMax(count);
    }

    public void setProgress(int progress) {
        controller.setProgress(progress);
    }

    public interface OnChapterChangeListener {
        void onChange(boolean previous);

        void findTitle(int position);

        void setByPosition(int position);
    }

    public void setOnChapterChangeListener(OnChapterChangeListener onChapterChangeListener) {
        this.onChapterChangeListener = onChapterChangeListener;
    }
}
