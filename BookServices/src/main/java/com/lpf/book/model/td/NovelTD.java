package com.lpf.book.model.td;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NovelTD {
    private String name;
    private String count;
    private String uid;
    private String aName;
    private String aType;
}
