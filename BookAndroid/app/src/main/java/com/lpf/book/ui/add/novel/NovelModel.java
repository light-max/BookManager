package com.lpf.book.ui.add.novel;

import com.lpf.book.base.mvp.BaseModel;
import com.lpf.book.api.Api;

public class NovelModel extends BaseModel<NovelFragment> {
    public void getAllNovel(String w) {
        Api.allNovel(w).success(data -> {
            base.getV().getAdapter().setNewData(data);
        }).run();
    }
}
