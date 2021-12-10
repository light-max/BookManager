package com.lpf.book.ui.people.history;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class BorrowHistoryModel extends BaseModel<BorrowHistoryActivity> {
    public void getBorrowHistory() {
        Api.getBorrowHistory().success(data -> {
            base.getView().getAdapter().setNewData(data);
        }).run();
    }
}
