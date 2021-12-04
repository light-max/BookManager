package com.lpf.book.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Student;
import com.lpf.book.model.request.StudentTempData;
import com.lpf.book.service.StudentService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {
    @Resource
    StudentService service;

    @GetMapping({"/admin/student/list", "/admin/student/list/{n}", "/admin/student"})
    @ViewModelParameter(key = "view", value = "student")
    public String list(
            Model model, @PathVariable(required = false) Integer n,
            @RequestParam(value = "w", required = false) String w
    ) {
        Page<Student> page = service.list(n, w);
        List<Student> list = page.getRecords();
        Pager pager = new Pager(page, "/admin/student/list");
        if (w != null) {
            pager.setTailAppend("?w=" + w);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", list);
        model.addAttribute("w", w);
        return "/admin/student";
    }

    @GetMapping("/admin/student/add")
    @ViewModelParameter(key = "view", value = "student_add")
    public String addStudent(Model model) {
        return "/admin/student_add";
    }

    @PostMapping({"/admin/student/all", "/teacher/student/all"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void add(@RequestBody List<StudentTempData> students) {
        service.saveStudents(students);
    }

    @GetMapping({"/admin/student/password", "/teacher/student/password"})
    @ResponseBody
    public Result<String> getPassword(String uid) {
        return Result.success(service.getPassword(uid));
    }

    @PutMapping({"/admin/student/password", "/teacher/student/password"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setPassword(String uid, String password) {
        service.setPassword(uid, password);
    }

    @PutMapping({"/admin/student/name", "/teacher/student/name"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setName(String uid, String name) {
        service.setName(uid, name);
    }

    @PutMapping({"/admin/student/sid", "/teacher/student/sid"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setSid(String uid, String sid) {
        service.setSid(uid, sid);
    }

    @PutMapping({"/admin/student/des", "/teacher/student/des"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setDes(String uid, String des) {
        service.setDes(uid, des);
    }

    @PutMapping({"/admin/student/gender", "/teacher/student/gender"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setGender(String uid, Integer gender) {
        service.setGender(uid, gender);
    }

    @DeleteMapping({"/admin/student", "/teacher/student"})
    @ResponseBody
    @UseDefaultSuccessResponse
    public void deleteStudent(String uid) {
        service.delete(uid);
    }
}
