package com.lpf.book.data.result;

public class Student {
    private String uid;
    private String name;
    private String sid;
    private String des;
    private int gender;

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getSid() {
        return sid;
    }

    public String getDes() {
        return des;
    }

    public int getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", des='" + des + '\'' +
                ", gender=" + gender +
                '}';
    }
}
