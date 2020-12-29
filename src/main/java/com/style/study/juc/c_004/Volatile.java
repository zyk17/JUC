package com.style.study.juc.c_004;

import java.util.concurrent.TimeUnit;

/**
 * 修饰变量可见
 * @author zhangyuekun
 * @date 2020/12/10 21:17
 */
public class Volatile {

    /*volatile*/ boolean flag = true;

    public void m (){
        System.out.println("m start...");
        while (flag){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        Volatile t = new Volatile();
        new Thread(t::m, "Thread-001").start();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.flag = false;
    }

}
