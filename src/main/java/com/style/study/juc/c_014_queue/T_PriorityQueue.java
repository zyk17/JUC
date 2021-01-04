package com.style.study.juc.c_014_queue;

import java.util.PriorityQueue;

/**
 * 比较的队列
 * @author zhangsan
 * @date 2021/1/3 20:15
 */
public class T_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.add("c");
        queue.add("l");
        queue.add("g");
        queue.add("q");
        queue.add("1");
        queue.add("a");

        for (int i = 0; i < 6; i++) {
            System.out.println(queue.poll());
        }

    }

}
