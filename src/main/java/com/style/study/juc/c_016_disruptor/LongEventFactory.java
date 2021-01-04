package com.style.study.juc.c_016_disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhangsan
 * @date 2021/1/4 21:44
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
