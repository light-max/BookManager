package com.lpf.book.controller.student;

import com.lpf.book.model.entity.Account;
import com.lpf.book.service.RetuService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@Controller
public class SRetuController {
    @Resource
    RetuService service;

    @PostMapping("/student/retu")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void retu(
            @SessionAttribute("student") Account account,
            Integer borrowId
    ) {
        service.retuBook(account.getUid(), borrowId);
    }
}
