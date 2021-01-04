package com.style.study.juc.c_015_threadpool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhangsan
 * @date 2021/1/4 19:04
 */
public class T_ForkJoinPool {

    static int[] nums = new int[10000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        long start = System.currentTimeMillis();
        System.out.println(Arrays.stream(nums).sum());
        long end = System.currentTimeMillis();
        System.out.println("同步计算耗时： "  + (end - start) + "ms");
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        /*AddTask task = new AddTask(0, nums.length - 1);
        pool.execute(task);*/

        AddTask2 task2 = new AddTask2(0, nums.length - 1);
        pool.execute(task2);
        long start = System.currentTimeMillis();
        try {
            System.out.println(task2.get());
            long end = System.currentTimeMillis();
            System.out.println("分片计算耗时： "  + (end - start) + "ms");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不带返回值的任务RecursiveAction
     */
    static class AddTask extends RecursiveAction {

        int s;
        int e;

        public AddTask(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        protected void compute() {
            if(e - s <= MAX_NUM){
                long sum = 0L;
                for (int i = s; i < e; i++) {
                    sum += nums[i];
                }
            }else {
                int middle = s + (e - s)/2;

                AddTask sub1 = new AddTask(s, middle);
                AddTask sub2 = new AddTask(middle+1, e);
                sub1.fork();
                sub2.fork();
            }
        }
    }

    static class AddTask2 extends RecursiveTask<Long> {

        int s;
        int e;

        public AddTask2(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        protected Long compute() {
            if(e - s <= MAX_NUM){
                long sum = 0L;
                for (int i = s; i <= e; i++) {
                    sum += nums[i];
                }
                return sum;
            }else {
                int middle = s + (e - s)/2;

                AddTask2 sub1 = new AddTask2(s, middle);
                AddTask2 sub2 = new AddTask2(middle+1, e);
                sub1.fork();
                sub2.fork();
                try {
                    return sub1.get() + sub2.get();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (ExecutionException executionException) {
                    executionException.printStackTrace();
                }
            }
            return 0L;
        }
    }

}
