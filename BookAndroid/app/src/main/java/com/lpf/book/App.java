package com.lpf.book;

import android.app.Application;

import com.lpf.book.novel.FileManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileManager.applicationContext = getApplicationContext();
    }
}
