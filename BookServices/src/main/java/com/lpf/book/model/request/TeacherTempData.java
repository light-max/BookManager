package com.lpf.book.model.request;

import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMax;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMin;
import com.lpf.book.plugin.fieldcheck.annotation.StringNonNull;
import com.lpf.book.plugin.fieldcheck.interfaces.FieldCheckInterface;
import lombok.Getter;

@Getter
public class TeacherTempData implements FieldCheckInterface<TeacherTempData> {
    private String index;

    @StringLengthMin(msg = "账号长度不能小于${value}", trim = true, value = 4)
    @StringLengthMax(msg = "账号长度不能大于${value}", value = 32)
    @StringNonNull("密码不能为空")
    private String uid;

    @StringLengthMin(msg = "密码长度不能小于${value}", trim = true, value = 4)
    @StringLengthMax(msg = "密码长度不能大于${value}", value = 32)
    @StringNonNull("密码不能为空")
    private String password;

    @StringNonNull("教师姓名不能为空")
    @StringLengthMax(msg = "教师姓名最大长度不能超过${value}", value = 30)
    private String name;

    @StringLengthMax(msg = "描述字符不能超过${value}", value = 300)
    private String des;
}
