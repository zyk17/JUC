package com.style.study.juc.c_015_threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangsan
 * @date 2021/1/4 16:30
 */
public class T_ScheduledPool {

    public static void main(String[] args) {

        // super(corePoolSize, Integer.MAX_VALUE, DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS, new DelayedWorkQueue())
        // 核心线程池， 最大线程池为int最大值， 10ms不做事就回收， 使用DelayedWorkQueue任务队列即可以延时队列
        // 可以延时线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.scheduleAtFixedRate(() -> {
            /*try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

}
