package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Novel;
import com.lpf.book.model.result.NovelPart;
import com.lpf.book.model.td.NovelTD;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface NovelService extends IService<Novel> {
    Page<Novel> list(Integer n, String w);

    Page<Novel> list(Integer n, String uid, String w);

    List<NovelTD> get(List<Novel> list);

    void addNovelFile(String uid, MultipartFile file) throws IOException;

    void deleteNovel(String uid, String name);

    ResponseEntity<FileSystemResource> getFile(String name) throws UnsupportedEncodingException;

    NovelPart getPart(String name, Integer page, Integer size);
}
