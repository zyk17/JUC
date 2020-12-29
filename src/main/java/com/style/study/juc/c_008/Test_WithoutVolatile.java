package com.style.study.juc.c_008;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一个容器，两个线程，第一个线程往里面添加十个元素，第二个线程监控这个容器当它的大小达到5时停止监控
 * @author zhangyuekun
 * @date 2020/12/20 22:00
 */
public class Test_WithoutVolatile {

    // 不给外界用，就是内部做大小用的
    int size = 8;
    Integer[] elements = new Integer[size];
    volatile int curSize = 0;
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void add(Integer element) {
        ReentrantReadWriteLock.WriteLock lock = readWriteLock.writeLock();
        try {
            lock.lock();
            detectionSize();
            if(elements[curSize] == null) {
                // 第一个
                elements[curSize++] = element;
            }
        }finally {
            lock.unlock();
        }
    }

    private void detectionSize(){
        if(curSize == size){
            // 如果当前大小等于容器最大大小，就扩容
            size = size << 1;
            Integer[] newElements = new Integer[size];
            System.arraycopy(this.elements, 0, newElements, 0, this.elements.length);
            elements = newElements;
            System.out.println("扩容+1");
        }
    }

    public int size() {
        ReentrantReadWriteLock.ReadLock lock = readWriteLock.readLock();
        try {
            lock.lock();
            return curSize;
        }finally {
            lock.unlock();
        }
    }

    public int get(int index) {
        ReentrantReadWriteLock.ReadLock lock = readWriteLock.readLock();
        try {
            lock.lock();
            if(index > curSize-1){
                throw new IndexOutOfBoundsException(Test_WithoutVolatile.class.getName() + "#get(int) 下标越界");
            }
            return elements[index];
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test_WithoutVolatile t = new Test_WithoutVolatile();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                t.add(i);
//                System.out.println("add"+" " + i + ", 当前容器数据： " + Arrays.toString(t.elements));
                System.out.println("add"+" " + i);
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });
        Thread t2 = new Thread(()->{
            while(true) {
                if(t.size() == 5){
                    break;
                }
            }
            System.out.println("end of monitoring..");
        });
        t1.start();
        t2.start();


        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.size());
        for (int i = 0; i < 15; i++) {
            System.out.println(t.get(i));
        }*/
    }

}
