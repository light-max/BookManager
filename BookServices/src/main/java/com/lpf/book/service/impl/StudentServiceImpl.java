package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.AssertException;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.AccountMapper;
import com.lpf.book.mapper.StudentMapper;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Student;
import com.lpf.book.model.request.StudentTempData;
import com.lpf.book.plugin.fieldcheck.FieldCheckException;
import com.lpf.book.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    AccountMapper accountMapper;

    @Override
    public Page<Student> list(Integer n, String w) {
        if (w == null) {
            return page(new Page<>(n == null ? 1 : n, 8));
        } else {
            return page(new Page<>(n == null ? 1 : n, 8),
                    new QueryWrapper<Student>()
                            .lambda()
                            .like(Student::getUid, w)
                            .or().like(Student::getName, w)
                            .or().like(Student::getSid, w)
                            .or().like(Student::getDes, w)
            );
        }
    }

    @Override
    public void saveStudents(List<StudentTempData> temps) {
        List<Student> students = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        List<StudentTempData> t2 = new ArrayList<>();
        while (!temps.isEmpty()) {
            StudentTempData temp = temps.remove(0);
            for (StudentTempData data : temps) {
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
        for (StudentTempData data : t2) {
            try {
                data.check();
            } catch (FieldCheckException e) {
                throw new AssertException("序号为" + data.getIndex() + "的账号字段错误," + e.getMessage());
            }
            students.add(Student.builder()
                    .uid(data.getUid())
                    .name(data.getName())
                    .sid(data.getSid())
                    .des(data.getDes())
                    .gender(data.getGender())
                    .build());
            accounts.add(Account.builder()
                    .uid(data.getUid())
                    .password(data.getPassword())
                    .type(2)
                    .build());
        }
        for (Account account : accounts) {
            accountMapper.insert(account);
        }
        saveBatch(students);
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
        GlobalConstant.dataNotExists.notNull(account);
        account.setPassword(password);
        accountMapper.updateById(account.check());
    }

    @Override
    public void setName(String uid, String name) {
        Student student = getById(uid);
        GlobalConstant.dataNotExists.notNull(student);
        student.setName(name);
        updateById(student.check());
    }

    @Override
    public void setSid(String uid, String sid) {
        Student student = getById(uid);
        GlobalConstant.dataNotExists.notNull(student);
        student.setSid(sid);
        updateById(student.check());
    }

    @Override
    public void setDes(String uid, String des) {
        Student student = getById(uid);
        GlobalConstant.dataNotExists.notNull(student);
        student.setDes(des);
        updateById(student.check());
    }

    @Override
    public void setGender(String uid, Integer gender) {
        Student student = getById(uid);
        GlobalConstant.dataNotExists.notNull(student);
        student.setGender(gender);
        updateById(student.check());
    }

    @Override
    public void delete(String uid) {
        accountMapper.deleteById(uid);
        removeById(uid);
    }
}
