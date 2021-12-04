package com.lpf.book.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_retu")
public class Retu {
    @TableId
    private Integer borrowId;

    private Long initiate;

    private Long finish;

    /*** 还书状态: 0申请中,1同意,2结束 */
    private Integer status;
}
