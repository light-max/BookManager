package com.lpf.book.ui.read;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseModel;
import com.lpf.book.novel.parser.NovelParser;
import com.lpf.book.utils.PageFlipTextView;

import java.lang.reflect.Constructor;

public class ReadModel extends BaseModel<ReadActivity> {
    private NovelParser parser;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        Intent intent = this.base.getIntent();
        try {
            Class<NovelParser> parserClass = (Class<NovelParser>) intent
                    .getSerializableExtra("parser");
            Constructor<NovelParser> constructor = parserClass.getConstructor(String.class);
            parser = constructor.newInstance(intent.getStringExtra("name"));
            new Handler(Looper.getMainLooper())
                    .postDelayed(() -> {
                        getNovelPart(false, false);
                    }, 100);
            this.base.getView().setOnChapterChangeListener(new ReadView.OnChapterChangeListener() {
                @Override
                public void onChange(boolean previous) {
                    if (previous) {
                        previousNode();
                    } else {
                        nextNode();
                    }
                }

                @Override
                public void findTitle(int position) {
                }

                @Override
                public void setByPosition(int position) {
                    parser.setPageNumber(position);
                    getNovelPart(false, true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    public void getNovelPart(boolean tailPage, boolean animation) {
        parser.generateTask().success(data -> {
            PageFlipTextView view = base.getView().getContent();
            TextView title = base.getView().getTitle();
            view.setText(data.getContent(), tailPage, animation);
            title.setText((data.getPageNumber() + 1) + "/" + data.getMaxPageNumber());

            base.getView().setMaxChapterNumber(data.getMaxPageNumber());
            base.getView().setProgress(data.getPageNumber() + 1);
        }).run();
    }

    public void previousNode() {
        if (parser.getPageNumber() <= 0) {
            base.toast("没有上一页了");
        } else {
            parser.setPageNumber(parser.getPageNumber() - 1);
            getNovelPart(true, true);
        }
    }

    public void nextNode() {
        if (parser.getPageNumber() >= parser.getMaxPageNumber()) {
            base.toast("没有下一页了");
        } else {
            parser.setPageNumber(parser.getPageNumber() + 1);
            getNovelPart(false, true);
        }
    }
}
