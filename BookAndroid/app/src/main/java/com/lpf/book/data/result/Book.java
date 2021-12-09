package com.lpf.book.data.result;

public class Book {
    int id;
    private String name;
    private String author;
    private String des;

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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
