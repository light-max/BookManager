package com.lpf.book.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lpf.book.plugin.fieldcheck.annotation.DateFormat;
import com.lpf.book.plugin.fieldcheck.annotation.NumberEnum;
import com.lpf.book.plugin.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_borrow")
public class Borrow implements FieldCheckInterface<Borrow> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer bookId;

    private String uid;

    private Long initiate;

    private Long finish;

    @DateFormat("yyyyMMdd")
    private String begin;

    @DateFormat("yyyyMMdd")
    private String end;

    /**
     * 状态: 0请求中,1同意,2拒绝,3过期,4已被使用
     */
    @NumberEnum({0, 1, 2, 3, 4})
    private Integer status;
}
