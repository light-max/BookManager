package com.lpf.book.ui.shelf.novel;

import android.os.Bundle;

import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseModel;
import com.lpf.book.novel.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NovelShelfModel extends BaseModel<NovelShelfFragment> {
    @Override
    public void onStart(Base base, Bundle savedInstanceState) {
        File path = FileManager.getNovelPath();
        File[] files = path.listFiles();
        List<File> list = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                list.add(file);
            }
        }
        this.base.getV().getAdapter().setNewData(list);
    }
}
