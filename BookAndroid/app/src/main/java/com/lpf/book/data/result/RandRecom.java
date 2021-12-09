package com.lpf.book.data.result;

public class RandRecom {
    private int type;
    private int id;
    private String name;

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isBook() {
        return type == 1;
    }
}
