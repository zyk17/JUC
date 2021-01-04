package com.style.study.juc.c_015_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangsan
 * @date 2021/1/4 16:16
 */
public class T_FixedThreadPool {

    public static void main(String[] args) {

        // new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
        // 核心线程和最大线程是固定的， 没有回收之说， 使用任务队列是LinkedBlockingQueue上限为Integer最大值
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }

}
