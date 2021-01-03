package com.style.study.juc.c_013;

import java.util.List;
import java.util.Vector;

/**
 * @author zhangsan
 * @date 2021/1/3 16:21
 */
public class TickerSeller2 {

    // Vector实现
    // 虽然它的方法都是安全的，加锁的，但是我卖票的过程没有加锁
    // 所以可能 最后一张票还在，还没有卖出去呢，然后多个线程看到还有票就都进来了，结果只有一个人卖出去了，其他的人卖出了空
    private static List<String> tickers = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
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
