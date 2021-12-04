package com.lpf.book.model.entity;

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
@TableName("t_novel")
public class Novel implements FieldCheckInterface<Novel> {
    @TableId
    @StringNonNull("书籍名称不能为空")
    @StringLengthMax(msg = "书籍名称不能超过${value}个字符", value = 200)
    private String name;

    private String uid;

    private Long count;

    private Long createTime;
}
