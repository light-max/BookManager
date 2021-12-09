package com.lpf.book.ui.details.novel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.data.result.NovelDetails;
import com.lpf.book.novel.FileManager;

import java.io.File;

public class NovelDetailsView extends BaseView<NovelDetailsActivity> {
    private AlertDialog dialog;

    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
    }

    public void setData(NovelDetails data) {
        ImageView background = get(R.id.background);
        ImageView cover = get(R.id.cover);
        TextView name = get(R.id.name);
        TextView count = get(R.id.count);
        TextView read = get(R.id.read);
        TextView part = get(R.id.part);
        Glide.with(base).load(ExRequestBuilder.getUrl("/cover/novel/" + data.getName()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(background);
        Glide.with(base).load(ExRequestBuilder.getUrl("/cover/novel/" + data.getName()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(cover);
        name.setText(data.getName());
        count.setText("字数：" + data.getCountString());
        read.setText("阅读量：" + data.getReadCount());
        part.setText(data.getPart());
        click(R.id.open, () -> base.getModel().open(data.getName()));
        click(R.id.collect, () -> base.getModel().collect(data.getName()));
        click(R.id.download, () -> {
            File file = FileManager.getNovelPath(data.getName());
            if (file.exists()) {
                base.toast("已缓存");
            } else {
                base.getModel().download(data.getName());
            }
        });
    }

    public void setCheck(boolean isCollect) {
        TextView collect = get(R.id.collect);
        if (isCollect) {
            collect.setText("已收藏");
        } else {
            collect.setText("收藏");
        }
    }

    public void showDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
        }
        dialog = new AlertDialog.Builder(base)
                .setView(new ProgressBar(base))
                .setMessage("下载中")
                .show();
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void checkNovelIsDownload(String name) {
        File file = FileManager.getNovelPath(name);
        TextView download = get(R.id.download);
        download.setText(file.exists() ? "已下载" : "下载");
    }
}
