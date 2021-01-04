package com.style.study.juc.c_015_threadpool;

import java.util.concurrent.*;

/**
 * @author zhangsan
 * @date 2021/1/4 13:12
 */
public class T_Callable {

    public static void main(String[] args) {
        Callable<String> callable = () -> {
            return "Hello, Callable!";
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 提交一个异步的任务
        Future<String> future = executorService.submit(callable);
        try {
            // 阻塞的获取值
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

}
