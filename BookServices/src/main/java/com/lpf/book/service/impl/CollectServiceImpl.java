package com.lpf.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lpf.book.mapper.CollectMapper;
import com.lpf.book.model.entity.Collect;
import com.lpf.book.service.CollectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Override
    public void toggleCollect(String uid, String name) {
        Collect collect = getOne(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getUid, uid)
                .eq(Collect::getName, name));
        if (collect == null) {
            save(Collect.builder().uid(uid)
                    .name(name)
                    .build());
        } else {
            removeById(collect.getId());
        }
    }

    @Override
    public boolean check(String uid, String name) {
        Collect collect = getOne(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getUid, uid)
                .eq(Collect::getName, name));
        return collect != null;
    }

    @Override
    public List<Collect> getAllByUID(String uid) {
        return list(new QueryWrapper<Collect>()
                .lambda()
                .eq(Collect::getUid, uid));
    }
}
