package com.style.study.juc.c_007;

import java.util.concurrent.TimeUnit;

/**
 * 测试synchronized是否是可重入锁
 * 可重入锁，持有一把锁o，然后再调用需要获取锁o这个锁的（方法/代码块）时，可以直接获取
 * 简单的锁+1
 * @author zhangyuekun
 * @date 2020/12/20 17:14
 */
public class Test_Reentrant1 {

    synchronized void m1(){
        System.out.println("m1");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
    synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) {
        Test_Reentrant1 test = new Test_Reentrant1();
        new Thread(test::m1, "Thread-1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(test::m2, "Thread-2").start();
    }

}
