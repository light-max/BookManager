package com.lpf.book.util;

import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.model.entity.Account;

public class AccountUIDTools {
    public static String get(Account admin, Account student, Account teacher) {
        String uid = null;
        if (student != null) {
            uid = student.getUid();
        } else if (teacher != null) {
            uid = teacher.getUid();
        } else if (admin != null) {
            uid = admin.getUid();
        }
        GlobalConstant.noAccess.notNull(uid);
        return uid;
    }
}
