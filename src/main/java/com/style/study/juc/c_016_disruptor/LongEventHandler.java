package com.style.study.juc.c_016_disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author zhangsan
 * @date 2021/1/4 21:45
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public static int count = 0;

    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("["+Thread.currentThread().getName()+"]" + ",longEvent: ["+ longEvent +"]"
                + "sequence:" + sequence + "endOfBatch: " +endOfBatch);
    }

}
