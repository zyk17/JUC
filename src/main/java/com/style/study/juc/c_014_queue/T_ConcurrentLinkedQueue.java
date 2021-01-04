package com.style.study.juc.c_014_queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangsan
 * @date 2021/1/3 17:34
 */
public class T_ConcurrentLinkedQueue {

    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<String>();

        for (int i = 0; i < 10; i++) {
            queue.offer("a: " + i);
        }

        System.out.println("队列的长度：" + queue.size());

        System.out.println("poll： " + queue.poll());
        System.out.println("队列的长度：" + queue.size());

        System.out.println("peek： " + queue.peek());
        System.out.println("队列的长度：" + queue.size());

    }

}
