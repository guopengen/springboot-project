package com.yanger.start;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/3/4 0:52
 */
public class ThreadTest {
    public static void main(String[] args) throws Exception, InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(1);
        executor.setBeanName("mybean");
        executor.setThreadNamePrefix("mytask-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();

        List<FutureTask<String>> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FutureTask<String> futureTask = new FutureTask<>(() -> {
                System.out.println("hello world!");
               // Thread.sleep(2000);
                return "hello "+Thread.currentThread().getName();
            });
           /* executor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"hello world!");
                try{
                    Thread.sleep(2000);
                }catch (Exception e){

                }
                System.out.println(Thread.currentThread().getName()+"over");
                //return "hello "+Thread.currentThread().getName();
            });*/
            result.add(futureTask);
        }
        for (FutureTask<String> futureTask : result) {
            try {
                System.out.println("==========="+futureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        /*ListenableFuture<?> runnable = executor.submitListenable(() -> {
            Thread.sleep(1000);
            System.out.println("runnable");
            return "runnable result";
        });
        runnable.addCallback(new ListenableFutureCallback<Object>() {
            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("success "+o.toString());
            }
        });

        System.out.println(runnable.get());*/
        executor.shutdown();
    }
}
