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
//        long p1, p2, p3, p4, p5, p6, p7;
//        long p8, p9, p10, p11, p12, p13, p14;

        // 这个注释的效果几乎总是会给对象增加大量的空间开销。只有当时间/空间权衡的性能影响在本质上是值得的.
        // 保证这个数据绝对不会和别的在同一个缓存行. 启动该注解需要在启动时加上参数:   -XX:--RestrictContened
        // @Contended
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
