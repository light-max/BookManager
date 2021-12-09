package com.lpf.book.data.result;

public class BookDetails {
    private int id;
    private String name;
    private String author;
    private String des;
    private boolean enable;
    private String cover;

    public int getId() {
        return id;
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

    public boolean isEnable() {
        return enable;
    }

    public String getCover() {
        return cover;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", des='" + des + '\'' +
                ", enable=" + enable +
                '}';
    }
}
