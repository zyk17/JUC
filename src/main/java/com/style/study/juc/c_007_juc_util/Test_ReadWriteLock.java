package com.style.study.juc.c_007_juc_util;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 共享锁（读锁）和排他锁（写锁）
 * 互斥锁/排他锁： 我拿到锁别人都不能拿
 * 共享锁/读锁：   我拿到锁如果你也是读锁你可以一块执行
 * @author zhangyuekun
 * @date 2020/12/20 19:24
 */
public class Test_ReadWriteLock {

    static ReentrantLock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock, writeLock;
    static int value;

    static {
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    static void readMethod(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    static void writeMethod(Lock lock, int value){
        try {
            lock.lock();
            Thread.sleep(1000);
            Test_ReadWriteLock.value = value;
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        Runnable readRunnable = () -> readMethod(lock);
//        Runnable writeRunnable = () -> writeMethod(lock, new Random().nextInt());
        Runnable readRunnable = () -> readMethod(readLock);
        Runnable writeRunnable = () -> writeMethod(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeRunnable).start();
        }
    }

}
