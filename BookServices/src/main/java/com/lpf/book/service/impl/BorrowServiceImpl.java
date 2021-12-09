package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.*;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.model.request.StudentBorrowData;
import com.lpf.book.model.result.BorrowDetails;
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
        final String[] statuses = new String[]{
                "待处理",
                "已同意",
                "已拒绝",
                "已过期",
                "已被使用",
        };
        List<BorrowTD> list = new ArrayList<>();
        for (Borrow b : borrows) {
            Account account = accountMapper.selectById(b.getUid());
            String name = account.getUid();
            if (account.getType() == 1) {
                name = teacherMapper.selectById(account.getUid()).getName();
            } else if (account.getType() == 2) {
                name = studentMapper.selectById(account.getUid()).getName();
            }
            Book book = bookMapper.selectById(b.getBookId());
            list.add(BorrowTD.builder()
                    .id(b.getId())
                    .uid(b.getUid())
                    .name(name)
                    .bookId(b.getBookId())
                    .bookName(book.getName())
                    .bookAuthor(book.getAuthor())
                    .statues(statuses[b.getStatus()])
                    .time(String.format("%s-%s %d天",
                            b.getBegin(),
                            b.getEnd(),
                            LocalDate.parse(b.getEnd(), dateFormat).toEpochDay() - LocalDate.parse(b.getBegin(), dateFormat).toEpochDay()
                    ))
                    .build());
        }
        return list;
    }

    @Override
    public void agreeBorrow(Integer id) {
        Borrow borrow = getById(id);
        GlobalConstant.dataNotExists.notNull(borrow);
        GlobalConstant.borrowError.isNull(getOne(new QueryWrapper<Borrow>()
                .lambda()
                .eq(Borrow::getStatus, 1)
                .eq(Borrow::getBookId, borrow.getBookId())
        ));
        GlobalConstant.error.isTrue(borrow.getStatus() == 0);
        borrow.setStatus(1);
        borrow.setFinish(System.currentTimeMillis());
        updateById(borrow);
    }

    @Override
    public void refuseBorrow(Integer id) {
        Borrow borrow = getById(id);
        GlobalConstant.dataNotExists.notNull(borrow);
        GlobalConstant.error.isTrue(borrow.getStatus() == 0);
        borrow.setStatus(2);
        borrow.setFinish(System.currentTimeMillis());
        updateById(borrow);
    }

    @Override
    public List<BorrowDetails> getAllByStatus(String uid, Integer status) {
        List<Borrow> borrows = list(new QueryWrapper<Borrow>()
                .lambda()
                .eq(Borrow::getUid, uid)
                .eq(Borrow::getStatus, status));
        return new ArrayList<BorrowDetails>() {{
            for (Borrow b : borrows) {
                long day = LocalDate.parse(b.getEnd(), dateFormat).toEpochDay() - LocalDate.parse(b.getBegin(), dateFormat).toEpochDay();
                Book book = bookMapper.selectById(b.getBookId());
                add(BorrowDetails.builder()
                        .id(b.getId())
                        .bookId(b.getBookId())
                        .initiate(b.getInitiate())
                        .finish(b.getFinish())
                        .begin(b.getBegin())
                        .end(b.getEnd())
                        .name(book.getName())
                        .author(book.getAuthor())
                        .des(book.getDes())
                        .cover("/cover/book/" + b.getBookId())
                        .day((int) day)
                        .build());
            }
        }};
    }
}
