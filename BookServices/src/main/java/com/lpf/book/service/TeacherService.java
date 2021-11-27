package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Teacher;
import com.lpf.book.model.request.TeacherTempData;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    void saveTeachers(List<TeacherTempData> temps);

    void setName(String uid, String name);

    void setDes(String uid, String des);

    void delete(String delete);

    String getPassword(String uid);

    void setPassword(String uid, String password);
}
