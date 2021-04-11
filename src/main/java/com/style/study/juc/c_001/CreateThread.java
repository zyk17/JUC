package com.style.study.juc.c_001;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhangyuekun
 * @date 2020/12/6 14:15
 */
public class CreateThread {

    public static class DemoThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("一个demo例子.");
        }
    }

    public static class DemoThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("一个demo例子.");
        }
    }

    public static class DemoThread3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("一个demo例子");
            return "callable返回值";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new DemoThread1().start();
        new Thread(new DemoThread2()).start();
        FutureTask<String> task = new FutureTask<>(new DemoThread3());
        new Thread(task).start();

        System.out.println("??? " + task.get());
    }

}
