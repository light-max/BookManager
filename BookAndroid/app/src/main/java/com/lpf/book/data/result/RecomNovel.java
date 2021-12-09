package com.lpf.book.data.result;

public class RecomNovel {
    private String name;
    private long count;
    private long readCount;

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public long getReadCount() {
        return readCount;
    }

    public String getCountString() {
        int b = 1024;
        int k = b * 1024;
        int m = k * 1024;
        if (count <= b) {
            return count + "B";
        } else if (count <= k) {
            return count / b + "KB";
        } else if (count <= m) {
            return count / k + "MB";
        } else {
            return count / m + "GB";
        }
    }
}
