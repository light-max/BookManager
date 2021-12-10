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
    private int status;

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
        if (finish == 0) {
            return "未受理";
        }
        return format.format(new Date(finish));
    }

    public String getBorrowTime() {
        return begin + "至" + end + " 共" + day + "天";
    }

    public int getStatus() {
        return status;
    }

    public String getStatusString() {
        return new String[]{
                "请求中",
                "已同意",
                "已拒绝",
                "已过期",
                "已使用",
        }[status];
    }
}
