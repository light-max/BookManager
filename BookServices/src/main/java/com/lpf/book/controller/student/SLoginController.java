package com.lpf.book.controller.student;

import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.StudentMapper;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Student;
import com.lpf.book.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class SLoginController {
    @Resource
    LoginService service;

    @Resource
    StudentMapper studentMapper;

    @PostMapping("/student/login")
    @ResponseBody
    public Result<Student> login(String uid, String password, HttpSession session) {
        Result<Account> account = service.student(uid, password);
        if (account.isSuccess()) {
            session.setAttribute("student", account.getData());
        }
        return Result.success(studentMapper.selectById(uid));
    }

    @GetMapping("/student/notloggedin")
    @ResponseBody
    public void notloggedin() {
        GlobalConstant.noAccess.newException();
    }
}
