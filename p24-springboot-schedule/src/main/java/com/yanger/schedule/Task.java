package com.yanger.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计划任务Scheduled是通过一个线程池实现的。是一个多线程的调度。
 * SpringBoot会初始化一个线程池，线程池默认大小为1，专门用于执行计划任务。
 * 每个计划任务启动的时候，都从线程池中获取一个线程执行，如果发生异常，只是执行当前任务的线程发生异常，而不是计划任务调度线程发生异常。
 * 如果当前定时任务还未执行完成，当相同的定时任务又进入到执行周期时，不会触发新的定时任务
 * @author yanger
 * @description 定时任务执行类
 * @date 2019/10/15
 */
@EnableScheduling
@Component
public class Task {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "* * * * * ?")
    public void taskOne(){
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("现在时间：" + dateFormat.format(new Date()));

    }

}
