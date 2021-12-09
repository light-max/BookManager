package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecomBook {
    private String name;
    private String author;
    private String des;
    private int count;
    private int borrowNumber;
    private String cover;

    public void addBorrowNumber() {
        borrowNumber++;
    }
}
