package com.lpf.book.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMax;
import com.lpf.book.plugin.fieldcheck.annotation.StringNonNull;
import com.lpf.book.plugin.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_book")
public class Book implements FieldCheckInterface<Book> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @StringNonNull("书籍名称不能为空")
    @StringLengthMax(msg = "书籍名称不能超过${value}个字符", value = 100)
    private String name;

    @StringLengthMax(msg = "书籍作者不能超过${value}个字符", value = 50)
    private String author;

    @StringLengthMax(msg = "书籍简介不能超过${value}个字符", value = 300)
    private String des;
}
