package com.lpf.book.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Teacher;
import com.lpf.book.model.request.TeacherTempData;
import com.lpf.book.service.TeacherService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TeacherController {
    @Resource
    TeacherService service;

    @GetMapping({"/admin/teacher", "/admin/teacher/list", "/admin/teacher/list/{n}"})
    @ViewModelParameter(key = "view", value = "teacher")
    public String list(Model model, @PathVariable(required = false) Integer n) {
        Page<Teacher> page = service.page(new Page<>(n == null ? 1 : n, 8));
        List<Teacher> list = page.getRecords();
        Pager pager = new Pager(page, "/admin/teacher/list");
        model.addAttribute("pager", pager);
        model.addAttribute("list", list);
        return "/admin/teacher";
    }

    @PostMapping("/admin/teacher/all")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void add(@RequestBody List<TeacherTempData> teachers) {
        service.saveTeachers(teachers);
    }

    @PutMapping("/admin/teacher/name")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setName(String uid, String name) {
        service.setName(uid, name);
    }

    @PutMapping("/admin/teacher/des")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setDes(String uid, String des) {
        service.setDes(uid, des);
    }

    @DeleteMapping("/admin/teacher")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void delete(String uid) {
        service.delete(uid);
    }

    @GetMapping("/admin/teacher/password")
    @ResponseBody
    public Result<String> getPassword(String uid) {
        return Result.success(service.getPassword(uid));
    }

    @PutMapping("/admin/teacher/password")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setPassword(String uid, String password) {
        service.setPassword(uid, password);
    }
}
