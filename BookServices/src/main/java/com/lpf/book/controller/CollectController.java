package com.lpf.book.controller;

import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Collect;
import com.lpf.book.service.CollectService;
import com.lpf.book.util.AccountUIDTools;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CollectController {
    @Resource
    CollectService service;

    @PostMapping("/collect")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void collect(
            @SessionAttribute(value = "student", required = false) Account student,
            @SessionAttribute(value = "teacher", required = false) Account teacher,
            @SessionAttribute(value = "admin", required = false) Account admin,
            String name
    ) {
        String uid = AccountUIDTools.get(student, teacher, admin);
        service.toggleCollect(uid, name);
    }

    @GetMapping("/collect/check")
    @ResponseBody
    public Result<Boolean> check(
            @SessionAttribute(value = "student", required = false) Account student,
            @SessionAttribute(value = "teacher", required = false) Account teacher,
            @SessionAttribute(value = "admin", required = false) Account admin,
            String name
    ) {
        String uid = AccountUIDTools.get(student, teacher, admin);
        return Result.success(service.check(uid, name));
    }

    @GetMapping("/collect/all")
    @ResponseBody
    public Result<List<Collect>> getCollectAll(
            @SessionAttribute(value = "student", required = false) Account student,
            @SessionAttribute(value = "teacher", required = false) Account teacher,
            @SessionAttribute(value = "admin", required = false) Account admin
    ) {
        String uid = AccountUIDTools.get(student, teacher, admin);
        return Result.success(service.getAllByUID(uid));
    }
}
