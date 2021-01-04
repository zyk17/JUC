package com.style.study.juc.c_015_threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhangsan
 * @date 2021/1/4 13:25
 */
public class T_Future {

    public static void main(String[] args) {
        // 实现了RunnableFuture，即Runnable和Future
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            Thread.sleep(500);
            return "Hello FutureTask, RunnableFuture";
        });

        // 使用线程来运行它，因为他是Runnable接口
        new Thread(futureTask).start();

        try {
            // 阻塞获取
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
