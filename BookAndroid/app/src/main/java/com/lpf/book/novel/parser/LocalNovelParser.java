package com.lpf.book.novel.parser;

import com.lpf.book.async.Async;
import com.lpf.book.async.AsyncTaskError;
import com.lpf.book.novel.FileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class LocalNovelParser extends NovelParser {
    private int maxPageNumber = 0;
    private int pageNumber = 0;
    private String content = "";

    public LocalNovelParser(String name) {
        super(name);
    }

    @Override
    public Async.Builder<NovelParser> generateTask() {
        return Async.<NovelParser>builder().task(() -> {
            try {
                File file = FileManager.getNovelPath(name);
                long skip = (long) getPageNumber() * pageSize;
                InputStream in = new FileInputStream(file);
                in.skip(skip);
                byte[] bytes = new byte[pageSize];
                int len = in.read(bytes);
                content = new String(bytes, 0, len);
                in.close();
                return LocalNovelParser.this;
            } catch (Exception e) {
                return new AsyncTaskError("error", e);
            }
        });
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getMaxPageNumber() {
        if (maxPageNumber == 0) {
            File file = FileManager.getNovelPath(name);
            maxPageNumber = (int) (file.length() / pageSize) + 1;
        }
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
