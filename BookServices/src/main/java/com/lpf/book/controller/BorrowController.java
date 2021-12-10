package com.lpf.book.controller;

import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.request.StudentBorrowData;
import com.lpf.book.model.result.BorrowDetails;
import com.lpf.book.service.BorrowService;
import com.lpf.book.util.AccountUIDTools;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BorrowController {
    @Resource
    BorrowService service;

    @PostMapping("/borrow")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void borrow(
            @SessionAttribute(value = "student", required = false) Account student,
            @SessionAttribute(value = "teacher", required = false) Account teacher,
            @SessionAttribute(value = "admin", required = false) Account admin,
            Integer bookId, Integer day
    ) {
        String uid = AccountUIDTools.get(admin, student, teacher);
        service.borrowBook(new StudentBorrowData(uid, bookId, day));
    }

    @GetMapping("/borrow/book/all")
    @ResponseBody
    public Result<List<BorrowDetails>> getAllByStatus(
            @SessionAttribute(value = "student", required = false) Account student,
            @SessionAttribute(value = "teacher", required = false) Account teacher,
            @SessionAttribute(value = "admin", required = false) Account admin,
            @RequestParam(required = false) Integer status
    ) {
        String uid = AccountUIDTools.get(admin, student, teacher);
        return Result.success(service.getAllByStatus(uid, status));
    }
}
