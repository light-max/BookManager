package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.*;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.model.request.StudentBorrowData;
import com.lpf.book.model.td.BorrowTD;
import com.lpf.book.service.BorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Resource
    BookMapper bookMapper;

    @Resource
    AccountMapper accountMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Page<Borrow> list(Integer n, Integer status) {
        if (status == null) {
            return page(new Page<>(n == null ? 1 : n, 10));
        } else {
            return page(new Page<>(n == null ? 1 : n, 10),
                    new QueryWrapper<Borrow>()
                            .lambda()
                            .eq(Borrow::getStatus, status)
            );
        }
    }

    @Override
    public void borrowBook(StudentBorrowData data) {
        data.check();
        Book book = bookMapper.selectById(data.getBookId());
        GlobalConstant.dataNotExists.notNull(book);
        Borrow one = getOne(new QueryWrapper<Borrow>().lambda()
                .eq(Borrow::getStatus, 1)
                .eq(Borrow::getBookId, book.getId()));
        GlobalConstant.borrowError.isNull(one);
        LocalDate begin = LocalDate.now(TimeZone.getTimeZone("GMT+8:00").toZoneId());
        LocalDate end = begin.plusDays(data.getDay());
        save(Borrow.builder()
                .bookId(data.getBookId())
                .uid(data.getUid())
                .initiate(System.currentTimeMillis())
                .finish(0L)
                .status(0)
                .begin(begin.format(dateFormat))
                .end(end.format(dateFormat))
                .build());
    }

    @Override
    public List<BorrowTD> get(List<Borrow> borrows) {
        List<BorrowTD> list = new ArrayList<>();
        for (Borrow b : borrows) {
            list.add(BorrowTD.builder().build());
        }
        return list;
    }
}
