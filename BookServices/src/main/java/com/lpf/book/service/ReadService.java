package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Read;

public interface ReadService extends IService<Read> {
    void addCount(String name);
}
