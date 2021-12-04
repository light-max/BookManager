package com.lpf.book.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.entity.Retu;
import com.lpf.book.service.RetuService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RetuController {
    @Resource
    RetuService service;

    @GetMapping({"/admin/retu", "/admin/retu/list", "/admin/retu/list/{n}"})
    @ViewModelParameter(key = "view", value = "retu")
    public String list(Model model, @PathVariable(required = false) Integer n, Integer status) {
        Page<Retu> page = service.list(n, status);
        List<Retu> list = page.getRecords();
        Pager pager = new Pager(page, "/admin/retu/list");
        if (status != null) {
            pager.setTailAppend("?status=" + status);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", service.get(list));
        model.addAttribute("status", status);
        return "/admin/retu";
    }

    @PostMapping("/admin/retu/agree")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void agree(Integer id) {
        service.agreeRetu(id);
    }

    @PostMapping("/admin/retu/refuse")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void refuse(Integer id) {
        service.refuseRetu(id);
    }
}
