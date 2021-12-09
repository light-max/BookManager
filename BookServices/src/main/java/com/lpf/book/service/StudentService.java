package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Student;
import com.lpf.book.model.request.StudentTempData;

import java.util.List;

public interface StudentService extends IService<Student> {
    Page<Student> list(Integer n, String w);

    void saveStudents(List<StudentTempData> temps);

    String getPassword(String uid);

    void setPassword(String uid, String password);

    void setName(String uid, String name);

    void setSid(String uid, String sid);

    void setDes(String uid, String des);

    void setGender(String uid, Integer gender);

    void delete(String uid);

    void setDesAndGender(String uid, String des, int gender);
}
