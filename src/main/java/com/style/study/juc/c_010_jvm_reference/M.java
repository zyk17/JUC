package com.style.study.juc.c_010_jvm_reference;

/**
 * @author zhangyuekun
 * @date 2020/12/30 15:02
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize...");
    }
}
