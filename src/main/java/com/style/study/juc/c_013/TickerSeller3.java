package com.style.study.juc.c_013;

import java.util.List;
import java.util.Vector;

/**
 * @author zhangsan
 * @date 2021/1/3 16:21
 */
public class TickerSeller3 {

    // Vector实现
    // 针对上述情况那么我的卖票操作就得整个再加个锁
    // 明显卖票的操作安全了，但是这个过程也慢了许多
    private static List<String> tickers = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickers.add("票： " + (i + 1));
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickers) {
                        if (tickers.size() == 0) {
                            break;
                        }
                        // 模拟卖票过程中间操作
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("卖掉：" + tickers.remove(0));
                    }
                }
            }).start();
        }
    }

}
