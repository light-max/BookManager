package com.lpf.book.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMax;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMin;
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
@TableName("t_teacher")
public class Teacher implements FieldCheckInterface<Teacher> {
    @TableId
    @StringLengthMin(msg = "账号长度不能小于${value}", trim = true, value = 4)
    @StringLengthMax(msg = "账号长度不能大于${value}", value = 32)
    private String uid;

    @StringNonNull("教师姓名不能为空")
    @StringLengthMax(msg = "教师姓名最大长度不能超过${value}", value = 80)
    private String name;

    @StringLengthMax(msg = "描述字符不能超过${value}", value = 300)
    private String des;
}
