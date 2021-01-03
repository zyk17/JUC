package com.style.study.juc.c_013;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangsan
 * @date 2021/1/3 16:21
 */
public class TickerSeller4 {

    // Con
    // 针对上述情况那么我的卖票操作就得整个再加个锁
    // 明显卖票的操作安全了，但是这个过程也慢了许多
    private static ConcurrentLinkedQueue<String> tickers = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickers.add("票： " + (i + 1));
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String ticker = tickers.poll();
                    if (ticker == null) {
                        break;
                    }
                    // 模拟卖票过程中间操作
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("卖掉--" + ticker);
                }
            }).start();
        }
    }

}
