package com.style.study.juc.c_014;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhangsan
 * @date 2021/1/3 18:15
 */
public class T_BlockingQueue {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // 模拟5个生产者, 往队列里仍20个东西
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        queue.put(Thread.currentThread().getName() + "生产的消息：" + j);
//                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "生产者线程-" + i).start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                while (true){
                    try {
                        System.out.println(Thread.currentThread().getName() +"消费到消息： " + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "消费者线程-" + i).start();
        }

    }

}
