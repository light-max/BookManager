package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpf.book.constant.AssertException;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.AccountMapper;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    AccountMapper mapper;

    @Override
    public Result<Account> admin(String uid, String password) {
        LambdaQueryWrapper<Account> wrapper = new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getUid, uid)
                .eq(Account::getPassword, password);
        Account account = mapper.selectOne(wrapper);
        try {
            GlobalConstant.loginError.notNull(account);
            GlobalConstant.loginError1.isTrue(account.getType() <= 0);
            return Result.success(account);
        } catch (AssertException e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result<Account> teacher(String uid, String password) {
        LambdaQueryWrapper<Account> wrapper = new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getUid, uid)
                .eq(Account::getPassword, password);
        Account account = mapper.selectOne(wrapper);
        try {
            GlobalConstant.loginError.notNull(account);
            GlobalConstant.loginError1.isTrue(account.getType() <= 1);
            return Result.success(account);
        } catch (AssertException e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result<Account> student(String uid, String password) {
        LambdaQueryWrapper<Account> wrapper = new QueryWrapper<Account>()
                .lambda()
                .eq(Account::getUid, uid)
                .eq(Account::getPassword, password);
        Account account = mapper.selectOne(wrapper);
        try {
            GlobalConstant.loginError.notNull(account);
            GlobalConstant.loginError1.isTrue(account.getType() <= 2);
            return Result.success(account);
        } catch (AssertException e) {
            return Result.error(e.getMessage());
        }
    }
}
