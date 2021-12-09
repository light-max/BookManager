package com.lpf.book.ui.details.book;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.data.result.BookDetails;

public class BookDetailsView extends BaseView<BookDetailsActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
    }

    public void setBookDetails(BookDetails book) {
        ImageView icon = get(R.id.icon);
        TextView name = get(R.id.name);
        TextView author = get(R.id.author);
        TextView des = get(R.id.des);
        Button borrow = get(R.id.borrow);
        Glide.with(base).load(ExRequestBuilder.getUrl(book.getCover()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(icon);
        name.setText(book.getName());
        author.setText(book.getAuthor());
        des.setText(book.getDes());
        borrow.setText(book.isEnable() ? "借阅" : "已被借走了");
        borrow.setEnabled(book.isEnable());
        borrow.setOnClickListener(v -> {
            showDialog(book.getId());
        });
    }

    private void showDialog(int id) {
        EditText text = new EditText(base);
        text.setHint("请输入借阅天数");
        new AlertDialog.Builder(base)
                .setMessage("借阅书籍")
                .setView(text)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    try {
                        int day = Integer.parseInt(text.getText().toString());
                        base.getModel().borrowBook(id, day);
                    } catch (Exception e) {
                        base.toast("请输入正确的天数");
                    }
                }).show();

    }

    public void showSuccessDialog() {
        new AlertDialog.Builder(base)
                .setMessage("申请成功，等待管理员同意")
                .setPositiveButton("确定", null)
                .show();
    }
}
