package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.request.BookTempData;
import com.lpf.book.model.request.BookUpdateData;

import java.util.List;

public interface BookService extends IService<Book> {
    Page<Book> query(Integer n, String name, String author, String des);

    void saveBooks(List<BookTempData> temps);

    void updateById(BookUpdateData data);

    void delete(Integer id);
}
