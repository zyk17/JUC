package com.style.study.juc.c_016_disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @author zhangsan
 * @date 2021/1/4 21:38
 */
public class Main {

    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();


        int ringBufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, ringBufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());
        RingBuffer<LongEvent> ringBuffer = disruptor.start();

        long sequencer = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequencer);
            event.setValue(8888L);
        }finally {
            ringBuffer.publish(sequencer);
        }

        disruptor.shutdown();
    }

}
