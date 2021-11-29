package com.lpf.book.model.td;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BorrowTD {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private String uid;
    private String name;
    private String initiate;
    private String finish;
    private String begin;
    private String end;
    private String day;
}
