package com.lpf.book.novel.parser;

import com.google.gson.reflect.TypeToken;
import com.lpf.book.api.Api;
import com.lpf.book.async.Async;
import com.lpf.book.data.Res;
import com.lpf.book.data.result.NovelPart;
import com.lpf.book.net.result.Result;

public class OnlineNovelParser extends NovelParser {
    private int maxPageNumber = 0;
    private int pageNumber = 0;
    private String content = "";

    public OnlineNovelParser(String name) {
        super(name);
    }

    @Override
    public Async.Builder<NovelParser> generateTask() {
        return Async.<NovelParser>builder().task(() -> {
            Result result = Api.getNovelPart(name, pageNumber, pageSize).execute();
            Res<NovelPart> res = result.json(new TypeToken<Res<NovelPart>>() {
            }.getType());
            NovelPart data = res.getData();
            maxPageNumber = data.getPageCount();
            pageNumber = data.getPage();
            content = data.getContent();
            return OnlineNovelParser.this;
        });
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getMaxPageNumber() {
        return maxPageNumber;
    }

    @Override
    public void setPageNumber(int pageNumber) {
        this.pageNumber = Math.min(pageNumber, maxPageNumber);
        this.pageNumber = Math.max(0, pageNumber);
    }

    @Override
    public String getContent() {
        return content;
    }
}
