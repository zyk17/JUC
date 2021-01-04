package com.style.study.juc.c_016_disruptor;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangsan
 * @date 2021/1/4 21:38
 */
public class Main02 {

    public static void main(String[] args) {

        int ringBufferSize = 1024;
//        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, ringBufferSize, Executors.defaultThreadFactory());
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, ringBufferSize, DaemonThreadFactory.INSTANCE);

        // 100个消费者
        for (int i = 0; i < 100; i++) {
            disruptor.handleEventsWith(new LongEventHandler());
        }
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<LongEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, LongEvent event) {
                System.out.println("disruptor消费事件。。event: " + event);
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("disruptor启动异常。。");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("disruptor停止异常。。");
            }
        });
        RingBuffer<LongEvent> ringBuffer = disruptor.start();

        /*ringBuffer.publishEvent(new EventTranslator<LongEvent>() {
            @Override
            public void translateTo(LongEvent event, long sequence) {

            }
        });*/

        Random random = new Random();
        // 生产者
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    ringBuffer.publishEvent((event, sequence) -> {
                        event.setValue(random.nextLong());
                    });
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LongEventHandler.count);
        disruptor.shutdown();
    }

}
