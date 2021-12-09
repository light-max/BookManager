package com.lpf.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Book;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.model.entity.Novel;
import com.lpf.book.model.entity.Read;
import com.lpf.book.model.result.RandRecom;
import com.lpf.book.model.result.RecomBook;
import com.lpf.book.model.result.RecomNovel;
import com.lpf.book.service.BookService;
import com.lpf.book.service.BorrowService;
import com.lpf.book.service.NovelService;
import com.lpf.book.service.ReadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Controller
public class RecomController {
    @Resource
    NovelService novelService;

    @Resource
    BookService bookService;

    @Resource
    BorrowService borrowService;

    @Resource
    ReadService readService;

    @GetMapping("/recom/rand")
    @ResponseBody
    public Result<List<RandRecom>> randRecom() {
        final Random r = new Random();
        ArrayList<RandRecom> recoms = new ArrayList<RandRecom>() {{
            {
                List<Book> list = bookService.list();
                int max = Math.min(15, list.size());
                for (int i = 0; i < max; i++) {
                    Book book = list.remove(r.nextInt(list.size()));
                    add(RandRecom.builder()
                            .id(book.getId())
                            .type(1)
                            .name(book.getName())
                            .build());
                }
            }
            {
                List<Novel> list = novelService.list();
                int max = Math.min(15, list.size());
                for (int i = 0; i < max; i++) {
                    Novel novel = list.remove(r.nextInt(list.size()));
                    add(RandRecom.builder()
                            .id(0)
                            .type(2)
                            .name(novel.getName())
                            .build());
                }
            }
        }};
        List<RandRecom> list = new ArrayList<>();
        while (!recoms.isEmpty()) {
            list.add(recoms.remove(r.nextInt(recoms.size())));
        }
        return Result.success(list);
    }

    @GetMapping("/recom/book")
    @ResponseBody
    public Result<List<RecomBook>> recomBook() {
        List<RecomBook> recomBooks = new ArrayList<>();
        List<Borrow> borrows = borrowService.list();
        for (Borrow b : borrows) {
            Book book = bookService.getById(b.getBookId());
            int i = find(recomBooks, book);
            if (i == -1) {
                LambdaQueryWrapper<Book> wrapper = new QueryWrapper<Book>().lambda()
                        .eq(Book::getName, book.getName())
                        .eq(Book::getAuthor, book.getAuthor())
                        .eq(Book::getDes, book.getDes());
                recomBooks.add(RecomBook.builder()
                        .name(book.getName())
                        .author(book.getAuthor())
                        .des(book.getDes())
                        .borrowNumber(1)
                        .count(bookService.count(wrapper))
                        .cover("/cover/book/" + book.getId())
                        .build());
            } else {
                recomBooks.get(i).addBorrowNumber();
            }
        }
        recomBooks.sort(new Comparator<RecomBook>() {
            @Override
            public int compare(RecomBook o1, RecomBook o2) {
                return o2.getBorrowNumber() - o1.getBorrowNumber();
            }
        });
        return Result.success(recomBooks);
    }

    private int find(List<RecomBook> recomBooks, Book book) {
        for (int i = 0; i < recomBooks.size(); i++) {
            RecomBook rb = recomBooks.get(i);
            if (rb.getName().equals(book.getName()) &&
                    rb.getAuthor().equals(book.getAuthor()) &&
                    rb.getDes().equals(book.getDes())
            ) {
                return i;
            }
        }
        return -1;
    }

    @GetMapping("/recom/novel")
    @ResponseBody
    public Result<List<RecomNovel>> recomNovel() {
        List<Read> reads = readService.list(new QueryWrapper<Read>()
                .lambda()
                .orderByDesc(Read::getCount)
        );
        reads = reads.subList(0, Math.min(reads.size(), 19));
        List<RecomNovel> list = new ArrayList<>();
        for (Read read : reads) {
            list.add(RecomNovel.builder()
                    .name(read.getName())
                    .count(novelService.getById(read.getName()).getCount())
                    .readCount(read.getCount())
                    .build());
        }
        return Result.success(list);
    }
}
