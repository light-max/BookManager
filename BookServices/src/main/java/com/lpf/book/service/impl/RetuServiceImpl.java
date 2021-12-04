package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.*;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.model.entity.Retu;
import com.lpf.book.model.td.RetuTD;
import com.lpf.book.service.BorrowService;
import com.lpf.book.service.RetuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RetuServiceImpl extends ServiceImpl<RetuMapper, Retu> implements RetuService {
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Resource
    AccountMapper accountMapper;

    @Resource
    BorrowService borrowService;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    BookMapper bookMapper;

    @Override
    public Page<Retu> list(Integer n, Integer status) {
        if (status == null) {
            return page(new Page<>(n == null ? 1 : n, 8));
        } else {
            return page(new Page<>(n == null ? 1 : n, 8), new QueryWrapper<Retu>()
                    .lambda()
                    .eq(Retu::getStatus, status)
            );
        }
    }

    @Override
    public void retuBook(String uid, Integer borrowId) {
        Retu retu = getById(borrowId);
        if (retu == null) {
            Borrow borrow = borrowService.getById(borrowId);
            GlobalConstant.dataNotExists.notNull(borrow);
            GlobalConstant.error.isTrue(borrow.getUid().equals(uid));
            save(Retu.builder().borrowId(borrowId)
                    .initiate(System.currentTimeMillis())
                    .finish(0L)
                    .status(0)
                    .build());
        } else {
            Borrow borrow = borrowService.getById(retu.getBorrowId());
            GlobalConstant.error.isTrue(borrow.getUid().equals(uid));
            GlobalConstant.error.isFalse(retu.getStatus() == 1);
            retu.setInitiate(System.currentTimeMillis());
            retu.setFinish(0L);
            retu.setStatus(0);
            updateById(retu);
        }
    }

    @Override
    public List<RetuTD> get(List<Retu> retus) {
        final String[] statuses = new String[]{
                "待处理",
                "已同意",
                "已拒绝",
        };
        List<RetuTD> list = new ArrayList<>();
        for (Retu r : retus) {
            Borrow borrow = borrowService.getById(r.getBorrowId());
            Account account = accountMapper.selectById(borrow.getUid());
            String name = account.getUid();
            if (account.getType() == 1) {
                name = teacherMapper.selectById(account.getUid()).getName();
            } else if (account.getType() == 2) {
                name = studentMapper.selectById(account.getUid()).getName();
            }
            Book book = bookMapper.selectById(borrow.getBookId());
            list.add(RetuTD.builder()
                    .id(r.getBorrowId())
                    .uid(account.getUid())
                    .name(name)
                    .time(String.format("%s-%s %d天",
                            borrow.getBegin(),
                            borrow.getEnd(),
                            LocalDate.parse(borrow.getEnd(), dateFormat).toEpochDay()
                                    - LocalDate
                                    .parse(borrow.getBegin(), dateFormat)
                                    .toEpochDay()
                    ))
                    .bookId(book.getId())
                    .bookName(book.getName())
                    .bookAuthor(book.getAuthor())
                    .status(statuses[r.getStatus()])
                    .build());
        }
        return list;
    }

    @Override
    public void agreeRetu(Integer id) {
        Retu retu = getById(id);
        GlobalConstant.dataNotExists.notNull(retu);
        GlobalConstant.error.isTrue(retu.getStatus() == 0);
        retu.setStatus(1);
        retu.setFinish(System.currentTimeMillis());
        updateById(retu);
    }

    @Override
    public void refuseRetu(Integer id) {
        Retu retu = getById(id);
        GlobalConstant.dataNotExists.notNull(retu);
        GlobalConstant.error.isTrue(retu.getStatus() == 0);
        retu.setStatus(2);
        retu.setFinish(System.currentTimeMillis());
        updateById(retu);
    }
}
