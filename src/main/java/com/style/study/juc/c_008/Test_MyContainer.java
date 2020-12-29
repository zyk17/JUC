package com.style.study.juc.c_008;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangyuekun
 * @date 2020/12/22 10:18
 */
public class Test_MyContainer<T> {

    private LinkedList<T> list = new LinkedList<>();
    private final int max = 10;
    private int count;
    ReentrantLock lock = new ReentrantLock();
    Condition provider = lock.newCondition();
    Condition consumer = lock.newCondition();


    public void put(T t){

    }

    public void get(){

    }

}
