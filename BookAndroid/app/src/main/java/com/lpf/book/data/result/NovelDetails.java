package com.lpf.book.data.result;

public class NovelDetails {
    private String name;
    private long count;
    private long readCount;
    private String part;

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

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public long getReadCount() {
        return readCount;
    }

    public String getPart() {
        return part;
    }

    @Override
    public String toString() {
        return "NovelDetails{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", readCount=" + readCount +
                ", part='" + part + '\'' +
                '}';
    }
}
