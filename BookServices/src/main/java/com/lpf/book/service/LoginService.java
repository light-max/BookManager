package com.lpf.book.service;

import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;

public interface LoginService {
    Result<Account> admin(String uid, String password);

    Result<Account> teacher(String uid, String password);

    Result<Account> student(String uid, String password);
}
