package com.lpf.book.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.entity.Student;
import com.lpf.book.service.StudentService;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TStudentController {
    @Resource
    StudentService service;

    @GetMapping({"/teacher/student/list", "/teacher/student/list/{n}", "/teacher/student"})
    @ViewModelParameter(key = "view", value = "student")
    public String list(
            Model model, @PathVariable(required = false) Integer n,
            @RequestParam(value = "w", required = false) String w
    ) {
        Page<Student> page = service.list(n, w);
        List<Student> list = page.getRecords();
        Pager pager = new Pager(page, "/teacher/student/list");
        if (w != null) {
            pager.setTailAppend("?w=" + w);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", list);
        model.addAttribute("w", w);
        return "/teacher/student";
    }

    @GetMapping("/teacher/student/add")
    @ViewModelParameter(key = "view", value = "student_add")
    public String addStudent(Model model) {
        return "/teacher/student_add";
    }
}
