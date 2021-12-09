package com.lpf.book.novel;

import android.content.Context;

import java.io.File;

public class FileManager {
    private static final String novelPath = "novel";
    private static final String noveCoverPath = "novel/cover";
    public static Context applicationContext = null;

    public static File getFiles() {
        return applicationContext.getExternalFilesDir(null);
    }

    public static File getNovelPath() {
        File file = new File(getFiles(), novelPath);
        file.mkdirs();
        return file;
    }

    public static File getNovelCoverPath() {
        File file = new File(getFiles(), noveCoverPath);
        file.mkdirs();
        return file;
    }

    public static File getNovelPath(String name) {
        return new File(getNovelPath(), name);
    }

    public static File getNovelCoverPath(String name) {
        return new File(getNovelCoverPath(), name);
    }
}