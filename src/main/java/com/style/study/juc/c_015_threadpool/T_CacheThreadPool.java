package com.style.study.juc.c_015_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangsan
 * @date 2021/1/4 16:11
 */
public class T_CacheThreadPool {

    public static void main(String[] args) {

        // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()
        // 核心线程数为0， 最大线程数为int最大值， 60s没有做事就回收， 使用容量为0的队列即来一个任务必须有一个线程把他领走执行否则发任务的线程阻塞不能继续发
        // 没有上限的线程数量， 使用默认拒绝策略就是抛异常
        ExecutorService executorService = Executors.newCachedThreadPool();

    }

}
