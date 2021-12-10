package com.lpf.book.api;

import com.lpf.book.async.Async;
import com.lpf.book.data.result.AccountTypeInfo;
import com.lpf.book.data.result.Book;
import com.lpf.book.data.result.BookDetails;
import com.lpf.book.data.result.BorrowDetails;
import com.lpf.book.data.result.Collect;
import com.lpf.book.data.result.Novel;
import com.lpf.book.data.result.NovelDetails;
import com.lpf.book.data.result.RandRecom;
import com.lpf.book.data.result.RecomBook;
import com.lpf.book.data.result.RecomNovel;
import com.lpf.book.data.result.Student;
import com.lpf.book.data.result.Teacher;
import com.lpf.book.net.request.RequestBuilder;

import java.util.List;

public class Api {
    public static Async.Builder<Student> sLogin(String username, String password) {
        return ExRequestBuilder.post("/student/login")
                .form()
                .field("uid", username)
                .field("password", password)
                .<ExRequestBuilder>as()
                .async(Student.class);
    }

    public static Async.Builder<Teacher> tLogin(String username, String password) {
        return ExRequestBuilder.post("/teacher/login")
                .form()
                .field("uid", username)
                .field("password", password)
                .<ExRequestBuilder>as()
                .async(Teacher.class);
    }

    public static Async.Builder<List<Novel>> allNovel(String w) {
        if (w == null) {
            return ExRequestBuilder.get("/novel/all")
                    .<ExRequestBuilder>as()
                    .asyncList(Novel.class);
        } else {
            return ExRequestBuilder.get("/novel/all")
                    .param("w", w)
                    .<ExRequestBuilder>as()
                    .asyncList(Novel.class);
        }
    }

    public static Async.Builder<List<Book>> allBook(String name, String author, String des) {
        RequestBuilder builder = ExRequestBuilder.get("/book/all");
        if (name != null) {
            builder.param("name", name);
        }
        if (author != null) {
            builder.param("author", author);
        }
        if (des != null) {
            builder.param("des", des);
        }
        return builder.<ExRequestBuilder>as().asyncList(Book.class);
    }

    public static Async.Builder<List<RandRecom>> randRecom() {
        return ExRequestBuilder.get("/recom/rand").asyncList(RandRecom.class);
    }

    public static Async.Builder<List<RecomBook>> recomBook() {
        return ExRequestBuilder.get("/recom/book").asyncList(RecomBook.class);
    }

    public static Async.Builder<List<RecomNovel>> recomNovel() {
        return ExRequestBuilder.get("/recom/novel").asyncList(RecomNovel.class);
    }

    public static Async.Builder<NovelDetails> getNovel(String name) {
        return ExRequestBuilder.get("/novel/{name}")
                .path("name", name)
                .<ExRequestBuilder>as()
                .async(NovelDetails.class);
    }

    public static Async.Builder<?> toggleNovelCollect(String name) {
        return ExRequestBuilder.post(("/collect"))
                .param("name", name)
                .async();
    }

    public static Async.Builder<Boolean> checkNovelCollect(String name) {
        return ExRequestBuilder.get("/collect/check")
                .param("name", name)
                .<ExRequestBuilder>as()
                .async(Boolean.class);
    }

    public static Async.Builder<List<Collect>> getNovelCollect() {
        return ExRequestBuilder.get("/collect/all")
                .asyncList(Collect.class);
    }

    public static RequestBuilder getNovelStream(String name) {
        return ExRequestBuilder.get("/novel/txt/{name}")
                .path("name", name)
                .useStream();
    }

    public static RequestBuilder getNovelCoverStream(String name) {
        return ExRequestBuilder.get("/cover/novel/{name}")
                .path("name", name)
                .useStream();
    }

    public static Async.Builder<?> addNovelReadNumber(String name) {
        return ExRequestBuilder.post("/novel/read")
                .param("name", name)
                .async();
    }

    public static Async.Builder<BookDetails> getBookDetails(int id) {
        return ExRequestBuilder.get("/book/details/{id}")
                .path("id", id)
                .<ExRequestBuilder>as()
                .async(BookDetails.class);
    }

    public static Async.Builder<?> borrowBook(int id, int day) {
        return ExRequestBuilder.post("/borrow")
                .param("bookId", id)
                .param("day", day)
                .async();
    }

    public static Async.Builder<List<BorrowDetails>> getBorrowedBookAll() {
        return ExRequestBuilder.get(("/borrow/book/all"))
                .param("status", 1)
                .<ExRequestBuilder>as()
                .asyncList(BorrowDetails.class);
    }

    public static Async.Builder<List<BorrowDetails>> getBorrowHistory() {
        return ExRequestBuilder.get("/borrow/book/all")
                .asyncList(BorrowDetails.class);
    }

    public static Async.Builder<AccountTypeInfo> getAccountTypeInfo() {
        return ExRequestBuilder.get("/account/info")
                .<ExRequestBuilder>as()
                .async(AccountTypeInfo.class);
    }

    public static Async.Builder<?> setTeacherInfo(String name, String des) {
        return ExRequestBuilder.post("/teacher/info")
                .param("name", name)
                .param("des", des)
                .async();
    }

    public static Async.Builder<?> setStudentInfo(String des, int gender) {
        return ExRequestBuilder.post("/student/info")
                .param("des", des)
                .param("gender", gender)
                .async();
    }

    public static Async.Builder<?> logout() {
        return ExRequestBuilder.post("/account/logout").async();
    }

    public static Async.Builder<?> retuBook(int borrowId) {
        return ExRequestBuilder.post("/retu")
                .param("borrowId", borrowId)
                .async();
    }

    public static ExRequestBuilder getNovelPart(String name, int page, int size) {
        return ExRequestBuilder.get(("/novel/part/{name}"))
                .path("name", name)
                .param("page", page)
                .param("size", size)
                .as();
    }
}
