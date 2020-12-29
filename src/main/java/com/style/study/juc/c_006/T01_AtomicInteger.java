package com.style.study.juc.c_006;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangyuekun
 * @date 2020/12/19 15:15
 */
public class T01_AtomicInteger {

    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 100; i++) {
            if(count.get() >= 999){
                System.out.println("商品已卖完！！！");
                return;
            }
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(t::m, "Thread - " + i));
        }
        threads.forEach(Thread::start);
        threads.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }

}
