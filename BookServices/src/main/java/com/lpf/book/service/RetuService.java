package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Retu;
import com.lpf.book.model.td.RetuTD;

import java.util.List;

public interface RetuService extends IService<Retu> {
    Page<Retu> list(Integer n, Integer status);

    void retuBook(String uid, Integer borrowId);

    List<RetuTD> get(List<Retu> retus);

    void agreeRetu(Integer id);

    void refuseRetu(Integer id);
}
