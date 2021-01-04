package com.style.study.juc.c_015_threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程数不够执行任务失败的策略
 * @author zhangsan
 * @date 2021/1/4 15:31
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("失败了。。。");
        System.out.println("runable： " + r);
        System.out.println("executor：  " + executor);
    }

}
