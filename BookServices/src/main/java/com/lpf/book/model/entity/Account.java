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
@TableName("t_account")
public class Account implements FieldCheckInterface<Account> {
    /*** 账号id */
    @TableId
    @StringLengthMin(msg = "账号长度不能小于${value}", trim = true, value = 4)
    @StringLengthMax(msg = "账号长度不能大于${value}", value = 32)
    private String uid;

    /*** 登录密码 */
    @StringLengthMin(msg = "密码长度不能小于${value}", trim = true, value = 4)
    @StringLengthMax(msg = "密码长度不能大于${value}", value = 32)
    @StringNonNull("密码不能为空")
    private String password;

    /*** 账号类型: 0.管理员 1.教师 2.学生*/
    private Integer type;
}
