package com.lpf.book.data.result;

public class Teacher {
    private String uid;
    private String name;
    private String des;

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
