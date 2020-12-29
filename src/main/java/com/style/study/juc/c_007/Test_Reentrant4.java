package com.style.study.juc.c_007;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 *
 * @author zhangyuekun
 * @date 2020/12/20 17:14
 */
public class Test_Reentrant4 {

    Lock lock = new ReentrantLock(true);
//    Lock lock = new ReentrantLock();

    void m() {
        for (int i = 0; i < 50; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ": " + i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Test_Reentrant4 test = new Test_Reentrant4();
        for (int i = 0; i < 10; i++) {
            new Thread(test::m, "Thread-" + i).start();
            /*try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

}
