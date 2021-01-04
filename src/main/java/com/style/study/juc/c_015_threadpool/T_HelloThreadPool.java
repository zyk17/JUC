package com.style.study.juc.c_015_threadpool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangsan
 * @date 2021/1/4 15:07
 */
public class T_HelloThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(4),
                    Executors.defaultThreadFactory(),       // 默认的线程工厂
//                    new ThreadPoolExecutor.AbortPolicy()    // 没有空余线程了，抛异常的策略
//                    new ThreadPoolExecutor.DiscardOldestPolicy()    // 没有空余线程了，抛弃掉任务队列中最老的那个任务
//                    new ThreadPoolExecutor.DiscardPolicy()    // 没有空余线程了，就不添加了，抛弃掉这个任务
//                    new ThreadPoolExecutor.CallerRunsPolicy()    // 呼叫空闲的线程来帮忙做这个任务
                    new MyRejectedExecutionHandler()    // 呼叫空闲的线程来帮忙做这个任务
                );

        for (int i = 0; i < 8; i++) {
            executor.submit(new MyThread(i));
        }

        System.out.println(executor.getQueue().toString());

        executor.submit(new MyThread(100));

        System.out.println(executor.getQueue());

        executor.shutdown();

    }

    static class MyThread implements Runnable {

        int i = 0;

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":\t" + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyThread{" +
                    "i=" + i +
                    '}';
        }
    }

}
