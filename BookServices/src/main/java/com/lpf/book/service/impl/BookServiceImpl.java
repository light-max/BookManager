package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.BookMapper;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.model.request.BookTempData;
import com.lpf.book.model.request.BookUpdateData;
import com.lpf.book.model.result.BookDetails;
import com.lpf.book.plugin.fieldcheck.FieldCheckException;
import com.lpf.book.service.BookService;
import com.lpf.book.service.BorrowService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Resource
    BorrowService service;

    @Override
    public Page<Book> query(Integer n, String name, String author, String des) {
        if (name == null && author == null && des == null) {
            return page(new Page<>(n == null ? 1 : n, 8));
        } else {
            Map<SFunction<Book, String>, String> map = new HashMap<>();
            if (name != null && name.trim().length() > 0) {
                map.put(Book::getName, name);
            }
            if (author != null && author.trim().length() > 0) {
                map.put(Book::getAuthor, author);
            }
            if (des != null && des.trim().length() > 0) {
                map.put(Book::getDes, des);
            }
            Set<SFunction<Book, String>> set = map.keySet();
            List<SFunction<Book, String>> list = new ArrayList<>(set);
            LambdaQueryWrapper<Book> wrapper = new QueryWrapper<Book>()
                    .lambda();
            if (!list.isEmpty()) {
                wrapper.like(list.get(0), map.get(list.get(0)));
            }
            for (int i = 1; i < list.size(); i++) {
                SFunction<Book, String> sf = list.get(i);
                wrapper.and(w -> w.like(sf, map.get(sf)));
            }
            return page(new Page<>(n == null ? 1 : n, 8), wrapper);
        }
    }

    @Override
    public void saveBooks(List<BookTempData> temps) {
        List<Book> books = new ArrayList<>();
        for (BookTempData temp : temps) {
            try {
                temp.check();
                for (int i = 0; i < temp.getNumber(); i++) {
                    books.add(Book.builder()
                            .name(temp.getName())
                            .author(temp.getAuthor())
                            .des(temp.getDes())
                            .flag(temp.getUse() ? 1 : 0)
                            .build());
                }
            } catch (FieldCheckException e) {
                throw new FieldCheckException("序号为" + temp.getIndex()
                        + "的书籍发生错误, 错误信息是: " + e.getMessage());
            }
        }
        saveBatch(books);
    }

    @Override
    public void updateById(BookUpdateData data) {
        data.check();
        if (data.getAll() == 0) {
            updateById(Book.builder().flag(1)
                    .id(data.getId())
                    .name(data.getName())
                    .author(data.getAuthor())
                    .des(data.getDes())
                    .build());
        } else {
            Book book = getById(data.getId());
            GlobalConstant.dataNotExists.notNull(book);
            update(new UpdateWrapper<Book>().lambda()
                    .set(Book::getName, data.getName())
                    .set(Book::getAuthor, data.getAuthor())
                    .set(Book::getDes, data.getDes())
                    .eq(Book::getName, book.getName())
                    .eq(Book::getAuthor, book.getAuthor())
            );
        }
    }

    @Value("${book.cover.file-path}")
    private String coverPath;

    @Override
    public void delete(Integer id) {

    }

    @Override
    public BookDetails getBookDetails(Integer id) {
        Book book = getById(id);
        Borrow borrow = service.getOne(new QueryWrapper<Borrow>()
                .lambda()
                .eq(Borrow::getStatus, 1)
                .eq(Borrow::getBookId, id));
        return BookDetails.builder()
                .id(id)
                .name(book.getName())
                .author(book.getAuthor())
                .des(book.getDes())
                .enable(borrow == null)
                .cover("/cover/book/" + id)
                .build();
    }
}
