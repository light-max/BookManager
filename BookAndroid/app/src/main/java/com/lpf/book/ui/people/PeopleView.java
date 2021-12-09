package com.lpf.book.ui.people;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lpf.book.R;
import com.lpf.book.api.Api;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.ui.login.LoginActivity;
import com.lpf.book.ui.people.collect.CollectActivity;
import com.lpf.book.ui.people.download.DownloadActivity;
import com.lpf.book.ui.people.history.BorrowHistoryActivity;
import com.lpf.book.ui.people.uinfo.UserInfoActivity;

public class PeopleView extends BaseView<PeopleActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
        click(R.id.info, () -> this.base.open(UserInfoActivity.class));
        click(R.id.history, () -> this.base.open(BorrowHistoryActivity.class));
        click(R.id.collect, () -> this.base.open(CollectActivity.class));
        click(R.id.download, () -> this.base.open(DownloadActivity.class));
        click(R.id.logout, () -> {
            this.base.setResult(Activity.RESULT_OK);
            this.base.finish();
            Intent intent = new Intent(this.base, LoginActivity.class);
            intent.putExtra(LoginActivity.auto, false);
            this.base.startActivity(intent);
            Api.logout().run();
        });
    }
}
