package com.lpf.book.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Novel;
import com.lpf.book.model.result.NovelPart;
import com.lpf.book.service.NovelService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class NovelController {
    @Resource
    NovelService service;

    @GetMapping({"/admin/novel", "/admin/novel/list", "/admin/novel/list/{n}"})
    @ViewModelParameter(key = "view", value = "novel")
    public String list(Model model, @PathVariable(required = false) Integer n, String w) {
        Page<Novel> page = service.list(n, w);
        List<Novel> list = page.getRecords();
        Pager pager = new Pager(page, "/admin/novel/list");
        if (w != null) {
            pager.setTailAppend("?w=" + w);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", service.get(list));
        model.addAttribute("w", w);
        return "/admin/novel";
    }

    @PostMapping("/admin/novel")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void add(@SessionAttribute("admin") Account account, MultipartFile file) throws IOException {
        service.addNovelFile(account.getUid(), file);
    }

    @DeleteMapping("/admin/novel")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void delete(@SessionAttribute("admin") Account account, String name) {
        service.deleteNovel(account.getUid(), name);
    }

    @GetMapping(value = "/novel/txt/{name}", produces = "application/force-download")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getText(@PathVariable String name) throws UnsupportedEncodingException {
        return service.getFile(name);
    }

    @GetMapping("/novel/part/{name}")
    @ResponseBody
    public Result<NovelPart> getPart(
            @PathVariable String name, Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return Result.success(service.getPart(name, page, size));
    }
}
