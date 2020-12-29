package com.style.study.juc.c_006;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhangyuekun
 * @date 2020/12/20 16:43
 */
public class Test {

    private long count1 = 0L;
    private AtomicLong count2 = new AtomicLong(0);
    private LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        Test t = new Test();

        Thread[] threads1 = new Thread[1000];
        Thread[] threads2 = new Thread[1000];
        Thread[] threads3 = new Thread[1000];

        init(t, threads1, threads2, threads3);

        execut(threads1, "同步代码块递增");
        execut(threads2, "AtomicLong递增");
        execut(threads3, "LongAdder递增");

        System.out.println(t.count1+ "\t" +t.count2.longValue()+ "\t" +t.count3.longValue());
    }

    private static void init(Test t, Thread[] threads1, Thread[] threads2, Thread[] threads3) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threads1.length; i++) {
            threads1[i] = new Thread( ()->{
                    for (int j = 0; j < 100000; j++)
                        synchronized(Test.class) {
                            t.count1++;
                }
            }, "Sync-Thread-" + i);
        }

        for (int i = 0; i < threads2.length; i++) {
            threads2[i] = new Thread( ()->{
                for (int j = 0; j < 100000; j++)
                    t.count2.incrementAndGet();
            }, "Atomic-Thread-" + i);
        }

        for (int i = 0; i < threads3.length; i++) {
            threads3[i] = new Thread( ()->{
                for (int j = 0; j < 100000; j++)
                    t.count3.increment();
            }, "LongAdder-Thread-" + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("初始化3个线程池共耗时" + (endTime - startTime) + "ms");
    }

    private static void execut(Thread[] threads, String desc) {
        long startTime = System.currentTimeMillis();
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
        long endTime = System.currentTimeMillis();
        System.out.println(desc + "： 1k个体线程进行1w次 long递增共耗时: " + (endTime - startTime) + "ms");
    }

}
