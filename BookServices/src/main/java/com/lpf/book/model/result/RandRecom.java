package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RandRecom {
    /*** 1:书籍 2:小说 */
    private int type;
    private int id;
    private String name;
}
