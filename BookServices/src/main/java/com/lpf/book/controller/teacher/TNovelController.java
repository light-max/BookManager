package com.lpf.book.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Novel;
import com.lpf.book.service.NovelService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
public class TNovelController {
    @Resource
    NovelService service;

    @GetMapping({"/teacher/novel", "/teacher/novel/list", "/teacher/novel/list/{n}"})
    @ViewModelParameter(key = "view", value = "novel")
    public String list(
            Model model, @PathVariable(required = false) Integer n,
            String w, @SessionAttribute("teacher") Account account
    ) {
        Page<Novel> page = service.list(n, account.getUid(), w);
        List<Novel> list = page.getRecords();
        Pager pager = new Pager(page, "/teacher/novel/list");
        if (w != null) {
            pager.setTailAppend("?w=" + w);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", service.get(list));
        model.addAttribute("w", w);
        return "/teacher/novel";
    }

    @PostMapping("/teacher/novel")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void add(@SessionAttribute("teacher") Account account, MultipartFile file) throws IOException {
        service.addNovelFile(account.getUid(), file);
    }

    @DeleteMapping("/teacher/novel")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void delete(@SessionAttribute("teacher") Account account, String name) {
        service.deleteNovel(account.getUid(), name);
    }
}
