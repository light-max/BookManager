package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.mapper.ReadMapper;
import com.lpf.book.model.entity.Read;
import com.lpf.book.service.ReadService;
import org.springframework.stereotype.Service;

@Service
public class ReadServiceImpl extends ServiceImpl<ReadMapper, Read> implements ReadService {
    @Override
    public void addCount(String name) {
        Read read = getById(name);
        if (read == null) {
            save(Read.builder()
                    .name(name)
                    .count(1L)
                    .build());
        } else {
            read.setCount(read.getCount() + 1);
            updateById(read);
        }
    }
}
