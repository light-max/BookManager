package com.lpf.book.ui.add.recom.book;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class RecomBookModel extends BaseModel<RecomBookActivity> {
    public void getRecomBook() {
        Api.recomBook().success(data -> {
            base.getView().getAdapter().setNewData(data);
        }).run();
    }
}
