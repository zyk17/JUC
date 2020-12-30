package com.style.study.juc.c_010;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyuekun
 * @date 2020/12/30 15:30
 */
public class T_SoftReference {

    // 启动这个例子，附加额外启动参数。 -Xms20M Xmx20M 指定运行最大的堆内存和最小的堆内存都为20，即就分配20M的堆内存空间
    public static void main(String[] args) throws InterruptedException {
        // 创建占用10M的 软引用内存
        // m指向 byte[] 是软引用
        SoftReference<byte[]> m = new SoftReference<byte[]>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());

        // 垃圾回收没有直接回收掉byte[]
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(m.get());

        // 有创见一个15M的byte[]， 堆内存空间不够，然后就会回收掉
        byte[] bytes = new byte[15 * 1024 * 1024];

        System.out.println(m.get());

    }

}
