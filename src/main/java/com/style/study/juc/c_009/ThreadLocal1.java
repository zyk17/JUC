package com.style.study.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyuekun
 * @date 2020/12/30 14:24
 */
public class ThreadLocal1 {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           tl.set(new Person("张三"));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程中的Person： " +  tl.get().name);
        });
        Thread t2 = new Thread(() -> {
           tl.set(new Person("李四"));
            System.out.println(Thread.currentThread().getName() + "线程中的Person： " +  tl.get().name);
        });
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + "线程中的Person： " +  tl.get().name);
    }


}

class Person {

    public Person(String name) {
        this.name = name;
    }

    String name;

}
