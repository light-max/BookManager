package com.lpf.book.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lpf.book.model.entity.Borrow;
import com.lpf.book.service.BorrowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Component
public class BorrowBookTask implements CommandLineRunner {
    @Resource
    BorrowService service;

    /**
     * 申请中的任务，过期了设置过期状态
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void task1() {
        String toDay = LocalDate.now(TimeZone.getTimeZone("GMT+8:00").toZoneId())
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        service.update(new UpdateWrapper<Borrow>().lambda()
                .eq(Borrow::getStatus, 0)
                .lt(Borrow::getEnd, toDay)
                .set(Borrow::getStatus, 3)
        );
    }

    /**
     * 已同意的申请，过期了设置已被使用的状态
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void task2() {
        String toDay = LocalDate.now(TimeZone.getTimeZone("GMT+8:00").toZoneId())
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        service.update(new UpdateWrapper<Borrow>().lambda()
                .eq(Borrow::getStatus, 1)
                .lt(Borrow::getEnd, toDay)
                .set(Borrow::getStatus, 4)
        );
    }

    @Override
    public void run(String... args) throws Exception {
        task1();
        task2();
    }
}
