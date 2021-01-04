package com.style.study.juc.c_007_juc_util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 锁的支持
 * 可以让一个线程停下来（park()），然后放行(unpark())
 * 也可提前把这个线程放行，它就不会停下来
 * @author zhangyuekun
 * @date 2020/12/20 21:46
 */
public class Test_LockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i == 5){
                    // 5s后，将当前线程停放。。
                    LockSupport.park();
                }
            }
        });
        t.start();
        // 提前取消停放
//        LockSupport.unpark(t);

        // 8s后讲t线程取消停放
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("8 seconds later.");
        LockSupport.unpark(t);
    }

}
