package com.style.study.juc.c_014;

import java.util.concurrent.SynchronousQueue;

/**
 * 可以同步交换内容的队列
 * @author zhangsan
 * @date 2021/1/3 20:21
 */
public class T_SynchronousQueue {

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            // 放完之后就阻塞了====
            queue.put("aaa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
