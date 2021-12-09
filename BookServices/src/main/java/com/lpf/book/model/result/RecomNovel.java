package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecomNovel {
    private String name;
    private Long count;
    private Long readCount;
}
