package com.style.study.juc.c_007_juc_util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试synchronized是否是可重入锁
 * 可重入锁，持有一把锁o，然后再调用需要获取锁o这个锁的（方法/代码块）时，可以直接获取
 * 简单的锁+1
 * @author zhangyuekun
 * @date 2020/12/20 17:14
 */
public class Test_Reentrant3 {

    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            System.out.println("m1");
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        boolean flag = false;
        try {
            flag = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("是否在五秒内拿到了锁？ " + flag);
            if(!flag) {
                System.out.println("没有拿到锁拿到了锁，可以处理其他逻辑。");
                return;
            }
            System.out.println("拿到了锁， 获得了执行机会。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(flag)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test_Reentrant3 test = new Test_Reentrant3();
        new Thread(test::m1, "Thread-1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(test::m2, "Thread-2").start();
    }

}
