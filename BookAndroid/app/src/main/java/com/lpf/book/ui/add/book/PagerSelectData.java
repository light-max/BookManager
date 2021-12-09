package com.lpf.book.ui.add.book;

import androidx.lifecycle.MutableLiveData;

public class PagerSelectData extends MutableLiveData<Integer> {
    public static PagerSelectData instance;

    public static PagerSelectData getInstance() {
        if (instance == null) {
            instance = new PagerSelectData();
        }
        return instance;
    }
}
