package com.lpf.book.ui.shelf.borrow;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class BorrowShelfModel extends BaseModel<BorrowShelfFragment> {
    public void getBorrowedBook() {
        Api.getBorrowedBookAll().success(data -> {
            base.getV().setData(data);
        }).run();
    }
}
