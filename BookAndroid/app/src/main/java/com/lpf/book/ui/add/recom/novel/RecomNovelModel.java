package com.lpf.book.ui.add.recom.novel;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class RecomNovelModel extends BaseModel<RecomNovelActivity> {
    public void getRecomNovel() {
        Api.recomNovel().success(data -> {
            base.getView().getAdapter().setNewData(data);
        }).run();
    }
}
