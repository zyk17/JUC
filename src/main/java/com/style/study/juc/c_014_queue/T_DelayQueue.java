package com.style.study.juc.c_014_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangsan
 * @date 2021/1/3 19:21
 */
public class T_DelayQueue {

    static BlockingQueue<MyTask> queue = new DelayQueue<MyTask>();

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask task1 = new MyTask("task1", now + 2000);
        MyTask task2 = new MyTask("task2", now + 1500);
        MyTask task3 = new MyTask("task3", now + 500);
        MyTask task4 = new MyTask("task4", now + 2500);
        MyTask task5 = new MyTask("task5", now + 1000);

        queue.add(task1);
        queue.add(task2);
        queue.add(task3);
        queue.add(task4);
        queue.add(task5);

        System.out.println(queue);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take());
        }

    }

    static class MyTask implements Delayed {

        String name;
        long runTime;

        MyTask(String name, long runTime) {
            this.name = name;
            this.runTime = runTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            // 获得延迟的时间方法
            return unit.convert(runTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(o.getDelay(TimeUnit.MILLISECONDS) == getDelay(TimeUnit.MILLISECONDS)){
                return 0;
            }
            if(o.getDelay(TimeUnit.MILLISECONDS) > getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runTime=" + runTime +
                    '}';
        }
    }

}
