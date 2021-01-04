package com.style.study.juc.c_011_map;

import java.util.Hashtable;
import java.util.UUID;

/**
 * @author zhangyuekun
 * @date 2021/1/3 14:52
 */
public class T_HashTable {

    private static int count        = T_Constant.COUNT;
    private static int threadCount  = T_Constant.THREAD_COUNT;
    /** 每个线程的工作次数 */
    private static int workCount    = count/threadCount;

    private static UUID[] keys      = new UUID[count];
    private static UUID[] values    = new UUID[count];

    private static Hashtable<UUID, UUID> hashtable = new Hashtable();

    static {
        for (int i = 0; i < count; i++) {
            keys[i]     = UUID.randomUUID();
            values[i]   = UUID.randomUUID();
        }
    }


    public static void main(String[] args) {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread(i * workCount);
        }

        // ======================添加测试==============================
        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("添加测试完毕，共耗时：" + (end-start) + "ms，数组元素：" + hashtable.size() + "个");

        /*hashtable.forEach((k, v)->{
            if( hashtable.get(k) == null) {
                System.out.println("中间有添加错误的。");
            }
//            System.out.println(k + "            =>          " + v);
        });*/

        // ========================读取测试=============================
        // 测试1000个线程，每个线程读取1w次（key为第11个）元素，共需要耗时多久呢
        Thread[] readThreads = new Thread[1000];
        for (int i = 0; i < readThreads.length; i++) {
            readThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    hashtable.get(keys[j]);
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
            for (int i = start; i < start+workCount; i++) {
                hashtable.put(keys[i], values[i]);
            }
        }
    }

}
