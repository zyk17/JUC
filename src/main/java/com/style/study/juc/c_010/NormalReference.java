package com.style.study.juc.c_010;

/**
 * 强引用垃圾回收
 * @author zhangyuekun
 * @date 2020/12/30 15:02
 */
public class NormalReference {

    public static void main(String[] args) throws Throwable {
        M m = new M();
        m = null;
        System.gc();
//            m.finalize();
        System.out.println(m);
        System.in.read();
    }

}
