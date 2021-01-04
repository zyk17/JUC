package com.style.study.juc.c_015_threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangsan
 * @date 2021/1/4 15:47
 */
public class T_SingleThreadPool {

    public static void main(String[] args) {
        // 只有一个线程的线程池

        // new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
        // 核心线程1个，最大线程1个，不会被回收， 可以添加Integer最大值的任务数量
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":\t");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
