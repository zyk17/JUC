package com.style.study.juc.c_012;

import java.util.*;

/**
 * @author zhangyuekun
 * @date 2021/1/3 15:39
 */
public class T_SynchronizedList {

    private final static int count = T_Constant.COUNT;
    private final static int threadCount = T_Constant.THREAD_COUNT;
    private final static int gap = count/threadCount;
    private static List<UUID> list = Collections.synchronizedList(new ArrayList<>());

    private static final UUID[] values = new UUID[count];
    static {
        for (int i = 0; i < count; i++) {
            values[i] = UUID.randomUUID();
        }
    }

    public static void main(String[] args) {
        Thread[] writeThreads = new Thread[threadCount];
        for (int i = 0; i < writeThreads.length; i++) {
            writeThreads[i] = new MyThread(i * gap);
        }

        // ======================添加测试==============================
        long start = System.currentTimeMillis();
        for (Thread thread : writeThreads) {
            thread.start();
        }
        for (Thread thread : writeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("添加测试完毕，共耗时：" + (end-start) + "ms，数组元素：" + list.size() + "个");

        // ========================读取测试=============================
        // 测试1000个线程，每个线程读取1w次（key为第11个）元素，共需要耗时多久呢
        Thread[] readThreads = new Thread[1000];
        for (int i = 0; i < readThreads.length; i++) {
            readThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    list.get(j);
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread : readThreads) {
            thread.start();
        }
        for (Thread thread : readThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("读取测试完毕，共耗时：" + (end-start) + "ms");
    }

    static class MyThread extends Thread {
        public MyThread(int start){ this.start = start; }
        int start;

        @Override
        public void run() {
            for (int i = start; i < start+gap; i++) {
                list.add(values[i]);
            }
        }
    }

}
