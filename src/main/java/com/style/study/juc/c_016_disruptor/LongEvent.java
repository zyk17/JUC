package com.style.study.juc.c_016_disruptor;

/**
 * @author zhangsan
 * @date 2021/1/4 21:39
 */
public class LongEvent {

    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
