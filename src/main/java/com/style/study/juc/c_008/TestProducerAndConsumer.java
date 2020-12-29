package com.style.study.juc.c_008;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * 生产者消费者例子
 * @author zhangyuekun
 * @date 2020/12/23 12:11
 */
public class TestProducerAndConsumer {

    LinkedList list = new LinkedList<>();

    public synchronized void put(Object o){
        list.add(o);
    }

    public synchronized Object get(){
        while (list.size() == 0){
        }
        return list.pop();
    }

    public static void main(String[] args) {
        TestProducerAndConsumer test = new TestProducerAndConsumer();
        Thread consumer1 = new Thread(() -> {
            for (int j = 0; j < 75; j++) {
                System.out.println(Thread.currentThread().getName() + ": 消费到数据" + test.get());
            }
        }, "Consumer-1");
        Thread consumer2 = new Thread(() -> {
            for (int j = 0; j < 75; j++) {
                System.out.println(Thread.currentThread().getName() + ": 消费到数据" + test.get());
            }
        }, "Consumer-2");

        for (int i = 0; i < 15; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.put(new Object());
                    System.out.println(Thread.currentThread().getName()+": 生产第" + j + "条数据");
                    LockSupport.unpark(consumer1);
                    LockSupport.unpark(consumer2);
                }
            }, "Producter-" + i).start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer1.start();
        consumer2.start();

    }


}
