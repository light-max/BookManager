package com.lpf.book.controller.student;

import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.request.StudentBorrowData;
import com.lpf.book.model.result.BorrowDetails;
import com.lpf.book.service.BorrowService;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SBorrowController {
    @Resource
    BorrowService service;

    @PostMapping("/student/borrow")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void borrow(
            @SessionAttribute("student") Account account,
            Integer bookId, Integer day
    ) {
        service.borrowBook(new StudentBorrowData(account.getUid(), bookId, day));
    }

    @GetMapping("/student/borrow/book/all")
    @ResponseBody
    public Result<List<BorrowDetails>> getAllByStatus(
            @SessionAttribute("student") Account account, Integer status
    ) {
        return Result.success(service.getAllByStatus(account.getUid(), status));
    }
}
