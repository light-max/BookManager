package com.lpf.book.ui.add.recom;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class RecomModel extends BaseModel<RecomFragment> {
    public void getRandRecom() {
        Api.randRecom().success(data -> {
            base.getV().getAdapter().setNewData(data);
        }).run();
    }
}
