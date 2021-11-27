package com.lpf.book.plugin.fieldcheck.handler;

import com.lpf.book.plugin.fieldcheck.FieldCheckException;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMax;
import com.lpf.book.plugin.fieldcheck.annotation.StringLengthMin;

import java.lang.annotation.Annotation;

/**
 * 字符串长度处理程序
 */
public class StringLengthHandler implements BaseHandler {
    @Override
    public void process(Object field, Annotation rule) throws FieldCheckException {
        String string = String.valueOf(field);
        Class<? extends Annotation> annotationType = rule.annotationType();
        if (annotationType.equals(StringLengthMax.class)) {
            StringLengthMax a = (StringLengthMax) rule;
            string = a.trim() ? string.trim() : string;
            if (string.length() > a.value()) {
                throw new FieldCheckException(a.msg().replace("${value}", String.valueOf(a.value())));
            }
        } else if (annotationType.equals(StringLengthMin.class)) {
            StringLengthMin a = (StringLengthMin) rule;
            string = a.trim() ? string.trim() : string;
            if (string.length() < a.value()) {
                throw new FieldCheckException(a.msg().replace("${value}", String.valueOf(a.value())));
            }
        }
    }
}
