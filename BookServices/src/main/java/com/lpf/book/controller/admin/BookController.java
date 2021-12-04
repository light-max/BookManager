package com.lpf.book.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lpf.book.model.data.Pager;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.request.BookTempData;
import com.lpf.book.model.request.BookUpdateData;
import com.lpf.book.service.BookService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import com.lpf.book.util.ump.ViewModelParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Resource
    BookService service;

    @GetMapping({"/admin/book", "/admin/book/list", "/admin/book/list/{n}"})
    @ViewModelParameter(key = "view", value = "book")
    public String list(
            @PathVariable(required = false) Integer n,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String des,
            Model model
    ) {
        Page<Book> page = service.query(n, name, author, des);
        List<Book> list = page.getRecords();
        Pager pager = new Pager(page, "/admin/book/list");
        if (name != null || author != null || des != null) {
            String append = "";
            Map<String, String> map = new HashMap<>();
            if (name != null && name.trim().length() > 0) {
                map.put("name", name);
            }
            if (author != null && author.trim().length() > 0) {
                map.put("author", author);
            }
            if (des != null && des.trim().length() > 0) {
                map.put("des", des);
            }
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            Map.Entry<String, String> entry = iterator.next();
            append += "?" + entry.getKey() + "=" + entry.getValue();
            while (iterator.hasNext()) {
                entry = iterator.next();
                append += "&" + entry.getKey() + "=" + entry.getValue();
            }
            pager.setTailAppend(append);
        }
        model.addAttribute("pager", pager);
        model.addAttribute("list", list);
        model.addAttribute("name", name);
        model.addAttribute("author", author);
        model.addAttribute("des", des);
        return "/admin/book";
    }

    @GetMapping("/admin/book/add")
    @ViewModelParameter(key = "view", value = "book_add")
    public String add(Model model) {
        return "/admin/book_add";
    }

    @GetMapping("/admin/book/update/{id}")
    @ViewModelParameter(key = "view", value = "book")
    public String update(Model model, @PathVariable Integer id) {
        model.addAttribute("book", service.getById(id));
        return "/admin/book_update";
    }

    @PostMapping("/admin/book/all")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void add(@RequestBody List<BookTempData> books) {
        service.saveBooks(books);
    }

    @PutMapping("/admin/book")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void update(@RequestBody BookUpdateData data) {
        service.updateById(data);
    }

    @DeleteMapping("/admin/book")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void delete(Integer id) {
        service.delete(id);
    }
}
