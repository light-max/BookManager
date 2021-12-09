package com.lpf.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.lpf.book.constant.AssertException;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.AccountMapper;
import com.lpf.book.mapper.StudentMapper;
import com.lpf.book.mapper.TeacherMapper;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.*;
import com.lpf.book.model.result.AccountTypeInfo;
import com.lpf.book.service.BookService;
import com.lpf.book.service.NovelService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MobileApiController {
    @Resource
    NovelService novelService;

    @Resource
    BookService bookService;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    AccountMapper accountMapper;

    @GetMapping("/novel/all")
    @ResponseBody
    public Result<List<Novel>> allNovel(@RequestParam(required = false) String w) {
        if (w == null) {
            return Result.success(novelService.list());
        } else {
            return Result.success(novelService.list(new QueryWrapper<Novel>()
                    .lambda()
                    .like(Novel::getName, w))
            );
        }
    }

    @GetMapping("/book/all")
    @ResponseBody
    public Result<List<Book>> allBook(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String des
    ) {
        if (name == null && author == null && des == null) {
            return Result.success(bookService.list());
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
            return Result.success(bookService.list(wrapper));
        }
    }

    @PostMapping("/account/logout")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void logout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("teacher");
        session.removeAttribute("student");
    }

    @GetMapping("/account/info")
    @ResponseBody
    public Result<AccountTypeInfo> getAccountTypeInfo(
            @SessionAttribute(value = "admin", required = false) Account admin,
            @SessionAttribute(value = "teacher", required = false) Account teacher,
            @SessionAttribute(value = "student", required = false) Account student
    ) {
        if (admin != null) {
            return Result.success(AccountTypeInfo.builder().type(0).build());
        } else if (teacher != null) {
            Teacher bean = teacherMapper.selectById(teacher.getUid());
            return Result.success(AccountTypeInfo.builder().type(1).teacher(bean).build());
        } else if (student != null) {
            Student bean = studentMapper.selectById(student.getUid());
            return Result.success(AccountTypeInfo.builder().type(2).student(bean).build());
        } else {
            throw new AssertException(GlobalConstant.noAccess.getMessage());
        }
    }
}
