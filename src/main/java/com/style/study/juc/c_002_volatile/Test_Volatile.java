package com.style.study.juc.c_002_volatile;

import java.util.concurrent.TimeUnit;

/**
 *
 * 测试volatile的可见性
 *
 * @author zhangsan
 * @date 2021/4/7 23:09
 */
public class Test_Volatile {

    /**
     * 基础类型: volatile修饰的变量, 一处改变,其他线程都能够立马看见
     */
//    public static volatile  boolean running = true;

    /** 引用类型: 只能保证引用的可见性, 不能保证内部属性其他线程能够马上读到 */
    public static volatile A a = new A();
    public static class A {
        /*volatile */boolean running = true;
    }

    public static void m() {
        System.out.println("start");
        while (a.running) {
            // do something
        }
        System.out.println("end");
    }


    public static void main(String[] args) {
        new Thread(Test_Volatile::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        running = false;
        a.running = false;
    }

}
