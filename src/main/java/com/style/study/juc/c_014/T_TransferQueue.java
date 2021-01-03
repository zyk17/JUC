package com.style.study.juc.c_014;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zhangsan
 * @date 2021/1/3 20:26
 */
public class T_TransferQueue {

    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


//        for (int i = 0; i < 10; i++) {
            try {
                // 传递完数据也是阻塞了
                queue.transfer("aaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }


        /*new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

    }

}
