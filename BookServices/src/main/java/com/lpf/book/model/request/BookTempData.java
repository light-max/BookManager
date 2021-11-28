package com.lpf.book.model.request;

import com.lpf.book.plugin.fieldcheck.annotation.NumberMax;
import com.lpf.book.plugin.fieldcheck.annotation.NumberMin;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMax;
import com.lpf.book.plugin.fieldcheck.annotation.StringNonNull;
import com.lpf.book.plugin.fieldcheck.interfaces.FieldCheckInterface;
import lombok.Getter;

@Getter
public class BookTempData implements FieldCheckInterface<BookTempData> {
    private String index;

    @StringNonNull("书籍名称不能为空")
    @StringLengthMax(msg = "书籍名称不能超过${value}个字符", value = 100)
    private String name;

    @StringLengthMax(msg = "书籍作者不能超过${value}个字符", value = 50)
    private String author;

    @StringLengthMax(msg = "书籍简介不能超过${value}个字符", value = 1000)
    private String des;

    @NumberMin(msg = "至少要包含${value}本书", value = 1)
    @NumberMax(msg = "每次最多设置${value}本书", value = 1000)
    private Integer number = 0;

    private Boolean use = true;
}
