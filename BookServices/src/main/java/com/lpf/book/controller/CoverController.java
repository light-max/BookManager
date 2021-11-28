package com.lpf.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.BookMapper;
import com.lpf.book.model.entity.Book;
import com.lpf.book.util.FileTools;
import com.lpf.book.util.UseDefaultSuccessResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CoverController {
    @Resource
    BookMapper bookMapper;

    @Value("${book.cover.file-path}")
    private String coverPath;

    @GetMapping(value = "/cover/book/{id}", produces = "image/jpeg")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getBookCover(@PathVariable Integer id) {
        File file = FileTools.getFileMakePath(coverPath, id + ".jpg");
        if (file.exists()) {
            return ResponseEntity.ok(new FileSystemResource(file));
        }
        return ResponseEntity.ok(new FileSystemResource(new File("src\\main\\resources\\static\\img\\nocover.jpg")));
    }

    @PostMapping("/admin/cover/book/{id}")
    @ResponseBody
    @UseDefaultSuccessResponse
    public void setBookCover(@PathVariable Integer id, MultipartFile file, Boolean all) throws IOException {
        Book book = bookMapper.selectById(id);
        GlobalConstant.dataNotExists.notNull(book);
        if (all) {
            List<Book> books = bookMapper.selectList(new QueryWrapper<Book>()
                    .lambda()
                    .eq(Book::getName, book.getName())
                    .eq(Book::getAuthor, book.getAuthor()));
            for (Book b : books) {
                ImageIO.read(file.getInputStream());
                File iFile = FileTools.getFileMakePath(coverPath, b.getId() + ".jpg");
                FileTools.write(file.getInputStream(), iFile);
            }
        } else {
            ImageIO.read(file.getInputStream());
            File iFile = FileTools.getFileMakePath(coverPath, id + ".jpg");
            FileTools.write(file.getInputStream(), iFile);
        }
    }
}
