package com.style.study.juc.c_008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 一个容器，两个线程，第一个线程往里面添加十个元素，第二个线程监控这个容器当它的大小达到5时停止监控
 * @author zhangyuekun
 * @date 2020/12/20 22:00
 */
public class Test_WithoutLockSupport {

    List list = new ArrayList();

    public void add(Object element) {
        list.add(element);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Test_WithoutLockSupport t = new Test_WithoutLockSupport();
        Thread t2 = new Thread(()->{
            LockSupport.park();
            System.out.println("end of monitoring..");
        });
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                t.add(i);
                System.out.println("add"+" " + i);
                if(t.size() == 5){
                    LockSupport.unpark(t2);
                }
            }
        });
        t1.start();
        t2.start();


        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.size());
        for (int i = 0; i < 15; i++) {
            System.out.println(t.get(i));
        }*/
    }

}
