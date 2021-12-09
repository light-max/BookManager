package com.lpf.book.ui.add.book;

import androidx.lifecycle.MutableLiveData;

public class SearchValueData extends MutableLiveData<SearchValueData.Value> {
    public static SearchValueData instance;

    public static SearchValueData getInstance() {
        if (instance == null) {
            instance = new SearchValueData();
        }
        return instance;
    }

    public static class Value {
        public String name;
        public String author;
        public String des;

        public Value() {
        }

        public Value(String name, String author, String des) {
            this.name = name;
            this.author = author;
            this.des = des;
        }
    }
}
