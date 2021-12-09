package com.lpf.book.model.result;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDetails {
    private Integer id;
    private String name;
    private String author;
    private String des;
    private Boolean enable;
    private String cover;
}
