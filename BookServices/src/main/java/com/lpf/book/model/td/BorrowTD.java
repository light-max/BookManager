package com.lpf.book.model.td;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BorrowTD {
    private Integer id;
    private String uid;
    private String name;
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private String time;
    private String statues;
}
