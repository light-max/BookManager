package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NovelPart {
    private int page;
    private int pageCount;
    private int pageSize;
    private long wordCount;
    private String content;
}
