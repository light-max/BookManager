package com.lpf.book.ui.people.collect;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class CollectModel extends BaseModel<CollectActivity> {
    public void getNovelCollect() {
        Api.getNovelCollect().success(data -> {
            base.getView().getAdapter().setNewData(data);
        }).run();
    }
}
