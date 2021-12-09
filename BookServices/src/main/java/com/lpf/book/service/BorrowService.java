package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.model.request.StudentBorrowData;
import com.lpf.book.model.result.BorrowDetails;
import com.lpf.book.model.td.BorrowTD;

import java.util.List;

public interface BorrowService extends IService<Borrow> {
    Page<Borrow> list(Integer n, Integer status);

    void borrowBook(StudentBorrowData data);

    List<BorrowTD> get(List<Borrow> borrows);

    void agreeBorrow(Integer id);

    void refuseBorrow(Integer id);

    List<BorrowDetails> getAllByStatus(String uid, Integer status);
}
