package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NovelDetails {
    private String name;
    private long count;
    private long readCount;
    private String part;
}
