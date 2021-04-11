package com.style.study.juc.c_002_volatile;

import java.util.concurrent.CountDownLatch;

/**
 * 测试缓存行
 * 缓存行大小,2的整数幂个连续字节，一般为32-256个字节。最常见的缓存行大小是64个字节。
 *
 * @author zhangsan
 * @date 2021/4/11 20:58
 */
public class Test_CacheLine {

    public static class T {
        // long 类型64位, 8个字节, 共8个lang类型变量也就是64个字节
//        long[] nums = new long[7];
        long x;
    }

    static T[] arr = {new T(), new T()};

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread task1 = new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });
        Thread task2 = new Thread(() -> {
            for (int i = 0; i < 100_000_000; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });
        long s1 = System.nanoTime();
        task1.start();
        task2.start();
        long s2 = System.nanoTime();

        latch.await();
        System.out.println("运行完毕, 耗时: " + (s2 - s1) / 10_000);
        // 注释掉 161200ns
    }

}
