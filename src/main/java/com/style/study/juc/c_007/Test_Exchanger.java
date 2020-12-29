package com.style.study.juc.c_007;

import java.util.concurrent.Exchanger;

/**
 * 交换机
 * 两个线程阻塞，交换数据
 * @author zhangyuekun
 * @date 2020/12/20 21:21
 */
public class Test_Exchanger {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            try {
                String s1 = "s1";
                s1 = exchanger.exchange(s1);
                System.out.println(Thread.currentThread().getName() + " " + s1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1").start();

        new Thread(()->{
            try {
                String s2 = "s2";
                s2 = exchanger.exchange(s2);
                System.out.println(Thread.currentThread().getName() + " " + s2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-2").start();
    }

}
