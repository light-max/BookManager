package com.lpf.book.plugin.fieldcheck.handler;

import com.lpf.book.plugin.fieldcheck.FieldCheckException;
import com.lpf.book.plugin.fieldcheck.annotation.NumberMax;
import com.lpf.book.plugin.fieldcheck.annotation.NumberMin;

import java.lang.annotation.Annotation;

/**
 * 数字大小处理程序
 *
 * @author 李凤强
 */
public class NumberSizeHandler implements BaseHandler {
    /**
     * @throws FieldCheckException 对象不能转换为数字
     */
    @Override
    public void process(Object field, Annotation rule) throws FieldCheckException {
        try {
            long number = Long.parseLong(String.valueOf(field));
            Class<? extends Annotation> annotationType = rule.annotationType();
            if (annotationType.equals(NumberMax.class)) {
                NumberMax a = (NumberMax) rule;
                if (number > a.value()) {
                    throw new FieldCheckException(a.msg().replace("${value}", String.valueOf(a.value())));
                }
            }
            if (annotationType.equals(NumberMin.class)) {
                NumberMin a = (NumberMin) rule;
                if (number < a.value()) {
                    throw new FieldCheckException(a.msg().replace("${value}", String.valueOf(a.value())));
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(field + "无法转换为数字");
        }
    }
}
