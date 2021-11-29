package com.lpf.book.model.request;

import com.lpf.book.plugin.fieldcheck.annotation.NumberMax;
import com.lpf.book.plugin.fieldcheck.annotation.NumberMin;
import com.lpf.book.plugin.fieldcheck.interfaces.FieldCheckInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentBorrowData implements FieldCheckInterface<StudentBorrowData> {
    private String uid;

    private Integer bookId;

    @NumberMin(msg = "借阅时间不能小于${value}天", value = 1)
    @NumberMax(msg = "借阅时间不能超过${value}天", value = 365)
    private Integer day;
}
