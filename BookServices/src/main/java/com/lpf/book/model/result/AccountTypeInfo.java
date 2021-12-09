package com.lpf.book.model.result;

import com.lpf.book.model.entity.Student;
import com.lpf.book.model.entity.Teacher;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountTypeInfo {
    private int type;
    private Student student;
    private Teacher teacher;
}
