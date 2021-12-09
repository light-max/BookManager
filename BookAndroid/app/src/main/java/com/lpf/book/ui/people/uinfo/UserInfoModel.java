package com.lpf.book.ui.people.uinfo;

import com.lpf.book.api.Api;
import com.lpf.book.base.mvp.BaseModel;

public class UserInfoModel extends BaseModel<UserInfoActivity> {
    public void getAccountTypeInfo() {
        Api.getAccountTypeInfo().error((message, e) -> {
            base.toast(message);
        }).success(data -> {
            if (data.getType() == 0) {
                base.getView().setAdmin();
            } else if (data.getType() == 1) {
                base.getView().setTeacher(data.getTeacher());
            } else if (data.getType() == 2) {
                base.getView().setStudent(data.getStudent());
            }
        }).run();
    }
}
