package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.mapper.AccountMapper;
import com.lpf.book.mapper.NovelMapper;
import com.lpf.book.mapper.StudentMapper;
import com.lpf.book.mapper.TeacherMapper;
import com.lpf.book.model.entity.Account;
import com.lpf.book.model.entity.Novel;
import com.lpf.book.model.result.NovelPart;
import com.lpf.book.model.td.NovelTD;
import com.lpf.book.service.NovelService;
import com.lpf.book.util.FileTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {
    @Value("${novel.content.file-path}")
    private String novelPath;

    @Value("${novel.cover.file-path}")
    private String novelCoverPath;

    @Resource
    AccountMapper accountMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public Page<Novel> list(Integer n, String w) {
        if (w == null) {
            return page(new Page<>(n == null ? 1 : n, 6), new QueryWrapper<Novel>()
                    .lambda()
                    .orderByDesc(Novel::getCreateTime)
            );
        } else {
            return page(new Page<>(n == null ? 1 : n, 6),
                    new QueryWrapper<Novel>()
                            .lambda()
                            .like(Novel::getName, w)
                            .orderByDesc(Novel::getCreateTime)
            );
        }
    }

    @Override
    public Page<Novel> list(Integer n, String uid, String w) {
        if (w == null) {
            return page(new Page<>(n == null ? 1 : n, 6), new QueryWrapper<Novel>()
                    .lambda()
                    .eq(Novel::getUid, uid)
                    .orderByDesc(Novel::getCreateTime)
            );
        } else {
            return page(new Page<>(n == null ? 1 : n, 6),
                    new QueryWrapper<Novel>()
                            .lambda()
                            .eq(Novel::getUid, uid)
                            .like(Novel::getName, w)
                            .orderByDesc(Novel::getCreateTime)
            );
        }
    }

    @Override
    public List<NovelTD> get(List<Novel> novels) {
        List<NovelTD> list = new ArrayList<>();
        for (Novel n : novels) {
            Account account = accountMapper.selectById(n.getUid());
            String name = account.getUid();
            if (account.getType() == 1) {
                name = teacherMapper.selectById(account.getUid()).getName();
            } else if (account.getType() == 2) {
                name = studentMapper.selectById(account.getUid()).getName();
            }
            list.add(NovelTD.builder()
                    .name(n.getName())
                    .uid(n.getUid())
                    .aName(name)
                    .aType(new String[]{"管理员", "教师", "学生"}[account.getType()])
                    .count(getCount(n.getCount()))
                    .build());
        }
        return list;
    }

    private String getCount(long count) {
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

    @Override
    public void addNovelFile(String uid, MultipartFile file) throws IOException {
        GlobalConstant.dataNotExists.notNull(file);
        String pathname = Objects.requireNonNull(file.getOriginalFilename());
        String name = new File(pathname).getName();
        Novel novel = getById(name.substring(name.lastIndexOf('.')));
        GlobalConstant.novelExists.isNull(novel);
        File savePath = FileTools.getFileMakePath(novelPath, name);
        novel = Novel.builder()
                .name(name.substring(0, name.lastIndexOf('.')))
                .count(file.getSize())
                .uid(uid)
                .createTime(System.currentTimeMillis())
                .build();
        FileTools.write(file.getInputStream(), savePath);
        save(novel);
    }

    @Override
    public void deleteNovel(String uid, String name) {
        Account account = accountMapper.selectById(uid);
        Novel novel = getById(name);
        GlobalConstant.dataNotExists.notNull(account);
        GlobalConstant.dataNotExists.notNull(novel);
        if (account.getType() > 0) {
            GlobalConstant.noAccess.isTrue(account.getUid().equals(novel.getUid()));
        }
        removeById(name);
        File cover = FileTools.getFileMakePath(novelCoverPath, name + ".jpg");
        File content = FileTools.getFileMakePath(novelCoverPath, name + ".txt");
        cover.delete();
        content.delete();
    }

    @Override
    public ResponseEntity<FileSystemResource> getFile(String name) throws UnsupportedEncodingException {
        File path = FileTools.getFileMakePath(novelPath, name + ".txt");
        if (path.exists()) {
            name = new String(name.getBytes("GBK"), StandardCharsets.ISO_8859_1);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment;fileName=" + name + ".txt")
                    .body(new FileSystemResource(path));
        } else {
            return null;
        }
    }

    @Override
    public NovelPart getPart(String name, Integer page, Integer size) {
        File file = FileTools.getFileMakePath(novelPath, name + ".txt");
        GlobalConstant.dataNotExists.isTrue(file.exists());
        long wordCount = file.length();
        int pageSize = size == null ? 10000 : size;
        int pageCount = (int) (wordCount / pageSize);
        String content = "";
        try {
            InputStream in = new FileInputStream(file);
            in.skip(pageSize * page);
            byte[] bytes = new byte[pageSize];
            int read = in.read(bytes);
            content = new String(bytes, 0, read);
        } catch (IOException e) {
            GlobalConstant.dataNotExists.newException();
        }
        return NovelPart.builder()
                .page(page)
                .wordCount(wordCount)
                .pageCount(pageCount)
                .content(content)
                .build();
    }
}
