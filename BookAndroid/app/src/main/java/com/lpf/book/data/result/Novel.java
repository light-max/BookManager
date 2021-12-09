package com.lpf.book.data.result;

public class Novel {
    private String name;
    private String uid;
    private long count;
    private long createTime;

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public long getCount() {
        return count;
    }

    public long getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", count=" + count +
                ", createTime=" + createTime +
                '}';
    }
}
