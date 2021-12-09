package com.lpf.book.data.result;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowDetails {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private int id;
    private int bookId;
    private long initiate;
    private long finish;
    private String begin;
    private String end;
    private String name;
    private String author;
    private String des;
    private String cover;
    private int day;

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public long getInitiate() {
        return initiate;
    }

    public long getFinish() {
        return finish;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDes() {
        return des;
    }

    public String getCover() {
        return cover;
    }

    public int getDay() {
        return day;
    }

    public String getInitiateString() {
        return format.format(new Date(initiate));
    }

    public String getFinishString() {
        return format.format(new Date(finish));
    }

    public String getBorrowTime() {
        return begin + "至" + end + " 共" + day + "天";
    }
}
