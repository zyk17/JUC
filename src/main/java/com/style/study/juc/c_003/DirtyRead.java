package com.style.study.juc.c_003;

import java.util.concurrent.TimeUnit;

/**
 * 脏读， 线程执行同步代码块， 其他线程可以执行其他的代码块
 * 单开了一个线程去设值，其他线程去获取，是可以出现脏读的。
 * @author zhangyuekun
 * @date 2020/12/9 20:49
 */
public class DirtyRead {

    String name;
    double price;

    public synchronized void set(String name, double price){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.price = price;
    }

    public void get(){
        System.out.println(name + ": " + price);
    }

    public static void main(String[] args) {
        DirtyRead dirtyRead = new DirtyRead();
        new Thread(() -> {
            dirtyRead.set("张三", 100.0);
        }).start();
        dirtyRead.get();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dirtyRead.get();
    }

}
