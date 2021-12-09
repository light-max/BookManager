package com.lpf.book.controller.student;

import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.StudentMapper;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Student;
import com.lpf.book.service.LoginService;
import com.lpf.book.service.StudentService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class SLoginController {
    @Resource
    LoginService service;

    @Resource
    StudentMapper studentMapper;

    @Resource
    StudentService studentService;

    @PostMapping("/student/login")
    @ResponseBody
    public Result<Student> login(String uid, String password, HttpSession session) {
        Result<Account> account = service.student(uid, password);
        if (account.isSuccess()) {
            session.setAttribute("student", account.getData());
            return Result.success(studentMapper.selectById(uid));
        } else {
            return Result.error(account.getMessage());
        }
    }

    @GetMapping("/student/notloggedin")
    @ResponseBody
    public void notloggedin() {
        GlobalConstant.noAccess.newException();
    }

    @PostMapping("/student/info")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setStudentInfo(
            @SessionAttribute("student") Account account,
            String des, Integer gender
    ) {
        studentService.setDesAndGender(account.getUid(), des, gender);
    }
}
