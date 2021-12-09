package com.lpf.book.data.livedata;

import androidx.lifecycle.MutableLiveData;

public class AccountData extends MutableLiveData<String> {
    private static AccountData instance;

    public static AccountData getInstance() {
        if (instance == null) {
            instance = new AccountData();
        }
        return instance;
    }
}
