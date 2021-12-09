package com.lpf.book.ui.login;

import com.lpf.book.base.mvp.BaseModel;
import com.lpf.book.data.livedata.AccountData;
import com.lpf.book.data.livedata.StudentData;
import com.lpf.book.data.livedata.TeacherData;
import com.lpf.book.api.Api;
import com.lpf.book.ui.shelf.BookShelfActivity;

public class LoginModel extends BaseModel<LoginActivity> {
    public void login(boolean isStudent, String username, String password) {
        if (isStudent) {
            Api.sLogin(username, password)
                    .before(() -> base.getView().showDialog())
                    .after(() -> {
                        base.getView().dismissDialog();
                    })
                    .error(((message, e) -> base.toast(message)))
                    .success(data -> {
                        StudentData.getInstance().postValue(data);
                        base.getView().saveStatus();
                        base.toast("登录成功");
                        base.finish();
                        base.open(BookShelfActivity.class);
                        AccountData.getInstance().postValue(username);
                    })
                    .run();
        } else {
            Api.tLogin(username, password)
                    .before(() -> base.getView().showDialog())
                    .after(() -> base.getView().dismissDialog())
                    .error(((message, e) -> base.toast(message)))
                    .success(data -> {
                        TeacherData.getInstance().postValue(data);
                        base.getView().saveStatus();
                        base.toast("登录成功");
                        base.finish();
                        base.open(BookShelfActivity.class);
                        AccountData.getInstance().postValue(username);
                    })
                    .run();
        }
    }
}
