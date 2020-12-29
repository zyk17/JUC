package com.style.study.juc.c_005;

/**
 * @author zhangyuekun
 * @date 2020/12/19 14:39
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("start ...");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {

        }
        String str = "";
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            str += "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-" +
                    "sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-sssssssss-";
            if(i % 100000 == 0){
                System.out.println("10w输出一次： " + str.length());
            }
        }
        System.out.println("str = "+ str);
        System.out.println("end ...");
    }

}
