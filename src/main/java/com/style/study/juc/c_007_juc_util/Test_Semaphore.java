package com.style.study.juc.c_007_juc_util;

import java.util.concurrent.Semaphore;

/**
 * 做限流可用
 * permits is 0, 发车。
 * @author zhangyuekun
 * @date 2020/12/20 19:53
 */
public class Test_Semaphore {

    public static void main(String[] args) {
        // 定义一个有多少许可证的信号
        Semaphore semaphore = new Semaphore(2);
        new Thread(()->{
            try {
                // 拿到一张许可证
                semaphore.acquire();
                System.out.println("T1 Running");
                Thread.sleep(100);
                System.out.println("T1 Running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放（返还）许可证
                semaphore.release();
            }
        }).start();
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 Running");
                Thread.sleep(100);
                System.out.println("T2 Running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }

}
