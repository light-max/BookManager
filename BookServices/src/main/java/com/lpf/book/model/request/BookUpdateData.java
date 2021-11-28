package com.lpf.book.model.request;

import com.lpf.book.plugin.fieldcheck.annotation.NumberEnum;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMax;
import com.lpf.book.plugin.fieldcheck.annotation.StringNonNull;
import com.lpf.book.plugin.fieldcheck.interfaces.FieldCheckInterface;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookUpdateData implements FieldCheckInterface<BookUpdateData> {
    private Integer id;

    @StringNonNull("书籍名称不能为空")
    @StringLengthMax(msg = "书籍名称不能超过${value}个字符", value = 100)
    private String name;

    @StringLengthMax(msg = "书籍作者不能超过${value}个字符", value = 50)
    private String author;

    @StringLengthMax(msg = "书籍简介不能超过${value}个字符", value = 1000)
    private String des;

    @NumberEnum({0, 1})
    private Integer all;
}
