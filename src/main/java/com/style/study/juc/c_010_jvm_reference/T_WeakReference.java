package com.style.study.juc.c_010_jvm_reference;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * 软引用
 * @author zhangyuekun
 * @date 2020/12/30 16:10
 */
public class T_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        // 遇到gc就会被回收
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        // ThreadLocal对象set的时候，存到了当前现成的 ThreadLocal.ThreadLocalMap threadLocals = null;里边
        // ThreadLocalMap使用的是 继承了WeakReference的Entry然后Entry中的key时使用了软引用ThreadLocal对象，value还是强引用
        // 如果Entry的key是强引用的话，那么线程不死，线程的threadLocals就还会在，threadLocals指向了Entry，
        //    那么即使ThreadLocal清除了，为空了，Entry里的key:ThreadLocal对象还是不会被清除，即发生了内存泄漏，所以要使用软引用来清除key
        // 使用了软引用key，但value还是强引用，还是被 线程的threadLocals指向，还是不会被清除。也还会发生内存泄露。
        // 所以当ThreadLocal不用了应该把调用他的remove方法
        tl.set(new M());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threalocal.get: " + tl.get());
        tl.remove();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
