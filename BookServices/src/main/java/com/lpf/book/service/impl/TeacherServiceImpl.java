package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.AssertException;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.AccountMapper;
import com.lpf.book.mapper.TeacherMapper;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Teacher;
import com.lpf.book.model.request.TeacherTempData;
import com.lpf.book.plugin.fieldcheck.FieldCheckException;
import com.lpf.book.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Resource
    AccountMapper accountMapper;

    @Override
    public void saveTeachers(List<TeacherTempData> temps) {
        List<Teacher> teachers = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<TeacherTempData> t2 = new ArrayList<>();
        while (!temps.isEmpty()) {
            TeacherTempData temp = temps.remove(0);
            for (TeacherTempData data : temps) {
                if (temp.getUid().equals(data.getUid())) {
                    throw new AssertException(String.format("序号为%s的账号和序号位%s的账号重复",
                            temp.getIndex(), data.getIndex()));
                }
            }
            if (accountMapper.selectById(temp.getUid()) != null) {
                throw new AssertException("序号为" + temp.getIndex() + "的账号已存在");
            }
            t2.add(temp);
        }
        for (TeacherTempData data : t2) {
            try {
                data.check();
            } catch (FieldCheckException e) {
                throw new AssertException("序号为" + data.getIndex() + "的账号字段错误," + e.getMessage());
            }
            teachers.add(Teacher.builder()
                    .uid(data.getUid())
                    .name(data.getName())
                    .des(data.getDes())
                    .build());
            accounts.add(Account.builder()
                    .uid(data.getUid())
                    .password(data.getPassword())
                    .type(1)
                    .build());
        }
        for (Account account : accounts) {
            accountMapper.insert(account);
        }
        saveBatch(teachers);
    }

    @Override
    public void setName(String uid, String name) {
        Teacher teacher = getById(uid);
        GlobalConstant.dataNotExists.notNull(teacher);
        teacher.setName(name);
        updateById(teacher.check());
    }

    @Override
    public void setDes(String uid, String des) {
        Teacher teacher = getById(uid);
        GlobalConstant.dataNotExists.notNull(teacher);
        teacher.setDes(des);
        updateById(teacher.check());
    }

    @Override
    public void delete(String delete) {
        accountMapper.deleteById(delete);
        removeById(delete);
    }

    @Override
    public String getPassword(String uid) {
        Account account = accountMapper.selectById(uid);
        GlobalConstant.dataNotExists.notNull(account);
        return account.getPassword();
    }

    @Override
    public void setPassword(String uid, String password) {
        Account account = accountMapper.selectById(uid);
        account.setPassword(password);
        GlobalConstant.dataNotExists.notNull(account);
        accountMapper.updateById(account.check());
    }

    @Override
    public void setNameAndDes(String uid, String name, String des) {
        Teacher teacher = getById(uid);
        GlobalConstant.dataNotExists.notNull(teacher);
        teacher.setName(name);
        teacher.setDes(des);
        updateById(teacher.check());
    }
}
