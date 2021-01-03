package com.style.study.juc.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsan
 * @date 2021/1/3 16:21
 */
public class TickerSeller1 {

    // ArrayList实现，不行，因为它不是线程安全的
    private static List<String> tickers = new ArrayList<>();

    static {
        for (int i = 0; i < 9999; i++) {
            tickers.add("票： " + (i+1));
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    if(tickers.size() == 0) {
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
            }).start();
        }
    }

}
