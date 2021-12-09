package com.lpf.book.controller;

import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Collect;
import com.lpf.book.service.CollectService;
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

    @PostMapping("/student/collect")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void collect(@SessionAttribute("student") Account account, String name) {
        service.toggleCollect(account.getUid(), name);
    }

    @GetMapping("/student/collect/check")
    @ResponseBody
    public Result<Boolean> check(@SessionAttribute("student") Account account, String name) {
        return Result.success(service.check(account.getUid(), name));
    }

    @GetMapping("/student/collect/all")
    @ResponseBody
    public Result<List<Collect>> getCollectAll(@SessionAttribute("student") Account account) {
        return Result.success(service.getAllByUID(account.getUid()));
    }
}
