package com.lpf.book.ui.details.book;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class BookDetailsModel extends BaseModel<BookDetailsActivity> {
    public void getBookDetails(int id) {
        Api.getBookDetails(id).success(data -> {
            base.getView().setBookDetails(data);
        }).run();
    }

    public void borrowBook(int id, int day) {
        Api.borrowBook(id, day).error((message, e) -> {
            base.toast(message);
        }).success(() -> {
            base.getView().showSuccessDialog();
        }).run();
    }
}
