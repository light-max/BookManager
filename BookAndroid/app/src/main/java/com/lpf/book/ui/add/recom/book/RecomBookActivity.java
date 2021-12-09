package com.lpf.book.ui.add.recom.book;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lpf.book.R;
import com.lpf.book.base.activity.BaseActivity;
import com.lpf.book.ui.add.book.PagerSelectData;
import com.lpf.book.ui.add.book.SearchValueData;

public class RecomBookActivity extends BaseActivity<RecomBookModel, RecomBookView> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recom_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        getModel().getRecomBook();
        getView().getAdapter().setOnItemClickListener((data, position) -> {
            SearchValueData.getInstance().postValue(new SearchValueData.Value(
                    data.getName(), null, null
            ));
            finish();
            PagerSelectData.getInstance().postValue(1);
        });
    }
}
