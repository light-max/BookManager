package com.lpf.book.ui.add.book;

import android.os.Bundle;

import com.lpf.book.api.Api;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseModel;

public class BookModel extends BaseModel<BookFragment> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        SearchValueData.getInstance().observe(base, value -> {
            Api.allBook(value.name, value.author, value.des).success(data -> {
                this.base.getV().getAdapter().setNewData(data);
            }).run();
        });
    }
}
