package com.style.study.juc.c_008;

/**
 * 类 构造函数，代码块，静态代码块的执行顺序
 *      静态代码块-》代码块-》构造函数
 * 类的方法名是否可以和类名重复？
 *      可以
 * @author zhangyuekun
 * @date 2020/12/22 9:24
 */
public class A {

    public A(){
        System.out.println("A的构造函数");
    }
    {
        System.out.println("A的代码块");
    }
    static {
        System.out.println("A的静态代码块");
    }

}

class B extends A{

    public B(){
        System.out.println("B的构造函数");
    }
    {
        System.out.println("B的代码块");
    }
    static {
        System.out.println("B的静态代码块");
    }
    void B(){
        System.out.println("B类的B方法");
    }
}

class C{
    public static void main(String[] args) {
        new B().B();
    }
}
