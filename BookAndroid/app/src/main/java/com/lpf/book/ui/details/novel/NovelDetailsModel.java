package com.lpf.book.ui.details.novel;

import com.lpf.book.api.Api;
import com.lpf.book.async.Async;
import com.lpf.book.base.mvp.BaseModel;
import com.lpf.book.net.result.Result;
import com.lpf.book.novel.FileManager;
import com.lpf.book.novel.parser.OnlineNovelParser;
import com.lpf.book.ui.read.ReadActivity;
import com.lpf.book.utils.IOUtils;

import java.io.File;

public class NovelDetailsModel extends BaseModel<NovelDetailsActivity> {
    public void getNovelDetails(String name) {
        Api.getNovel(name).success(data -> {
            base.getView().setData(data);
        }).run();
    }

    public void check(String name) {
        Api.checkNovelCollect(name).success(data -> {
            base.getView().setCheck(data);
        }).run();
    }

    public void open(String name) {
        ReadActivity.start(OnlineNovelParser.class, base, name);
    }

    public void collect(String name) {
        Api.toggleNovelCollect(name).success(() -> {
            check(name);
        }).run();
    }

    public void download(String name) {
        Async.builder().task(() -> {
            File novelFile = FileManager.getNovelPath(name);
            Result novelResult = Api.getNovelStream(name).execute();
            IOUtils.write(novelResult.inputStream(), novelFile);
            File novelCoverFile = FileManager.getNovelCoverPath(name);
            Result novelCoverResult = Api.getNovelCoverStream(name).execute();
            IOUtils.write(novelCoverResult.inputStream(), novelCoverFile);
        }).error((e, s) -> {
            base.toast("下载失败");
        }).success(() -> {
            base.toast("下载成功");
        }).before(() -> {
            base.getView().showDialog();
        }).after(() -> {
            base.getView().dismissDialog();
            base.getView().checkNovelIsDownload(name);
        }).run();
    }

    public void addReadNumber(String name){
        Api.addNovelReadNumber(name).run();
    }
}
