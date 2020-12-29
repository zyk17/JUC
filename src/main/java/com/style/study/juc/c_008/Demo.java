package com.style.study.juc.c_008;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyuekun
 * @date 2020/12/23 22:33
 */
public class Demo {

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);

        for (;;){
            // 线程池创建一个线程，遍历一百次执行对象m方法
            modelFit();
            Thread.sleep(1000);
        }
    }

    private static class CardInfo{
        BigDecimal price = new BigDecimal("0.0");
        String name = "张三";
        int age = 5;
        Date birthDate = new Date();

        public void m(){
            System.out.println(Thread.currentThread().getName() + ", hi");
        }
    }

    private static void modelFit(){
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(cardInfo -> executor.scheduleWithFixedDelay(cardInfo::m, 2, 3, TimeUnit.SECONDS));
    }

    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            CardInfo cardInfo = new CardInfo();
            taskList.add(cardInfo);
        }
        return taskList;
    }

}
