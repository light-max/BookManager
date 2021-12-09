package com.lpf.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lpf.book.model.entity.Collect;

import java.util.List;

public interface CollectService extends IService<Collect> {
    void toggleCollect(String uid, String name);

    boolean check(String uid, String name);

    List<Collect> getAllByUID(String uid);
}
