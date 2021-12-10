package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BorrowDetails {
    private Integer id;
    private Integer bookId;
    private Long initiate;
    private Long finish;
    private String begin;
    private String end;
    private String name;
    private String author;
    private String des;
    private String cover;
    private Integer day;
    private Integer status;
}
