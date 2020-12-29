package com.style.study.juc.c_006;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

/**
 * @author zhangyuekun
 * @date 2020/12/19 15:15
 */
public class T02_Unsafe {


    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("sun.misc.Unsafe");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe = (Unsafe) constructor.newInstance();

        int count = 1;
        boolean flag = unsafe.compareAndSwapInt(count, 1, 1, 2);
        System.out.println(flag);
    }

}
