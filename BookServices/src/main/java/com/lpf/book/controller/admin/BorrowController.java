package com.lpf.book.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.service.BorrowService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BorrowController {
    @Resource
    BorrowService service;

    @GetMapping({"/admin/borrow", "/admin/borrow/list", "/admin/borrow/list/{n}"})
    @ViewModelParameter(key = "view", value = "borrow")
    public String list(
            Model model, @PathVariable(required = false) Integer n,
            @RequestParam(required = false) Integer status
    ) {
        Page<Borrow> page = service.list(n, status);
        List<Borrow> list = page.getRecords();
        Pager pager = new Pager(page, "/admin/borrow/list");
        if (status != null) {
            pager.setTailAppend("?status=" + status);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", service.get(list));
        model.addAttribute("status", status);
        return "/admin/borrow";
    }

    @PostMapping("/admin/borrow/agree")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void agree(Integer id) {
        service.agreeBorrow(id);
    }

    @PostMapping("/admin/borrow/refuse")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void refuse(Integer id) {
        service.refuseBorrow(id);
    }
}
