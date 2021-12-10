package com.lpf.book.novel.parser;

import com.lpf.book.async.Async;

public abstract class NovelParser {
    protected String name;
    protected int pageSize = 1024 * 8;

    public NovelParser(String name) {
        this.name = name;
    }

    public abstract Async.Builder<NovelParser> generateTask();

    public String getName() {
        return name;
    }

    public abstract int getPageNumber();

    public abstract int getMaxPageNumber();

    public abstract void setPageNumber(int pageNumber);

    public abstract String getContent();
}
