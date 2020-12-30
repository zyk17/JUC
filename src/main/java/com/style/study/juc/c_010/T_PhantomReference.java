package com.style.study.juc.c_010;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用
 * @author zhangyuekun
 * @date 2020/12/30 17:34
 */
public class T_PhantomReference {

    static ReferenceQueue QUEUE =new ReferenceQueue();
    static List LIST = new ArrayList();

    public static void main(String[] args) throws InterruptedException {
        PhantomReference phantomReference = new PhantomReference(new M(), QUEUE);
        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference poll = QUEUE.poll();
                if(poll != null){
                    System.out.println("虚引用被垃圾回收掉了--" + poll);
                }
            }
        }).start();


    }

}
