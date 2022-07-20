package com.mashibing.juc.a05_ordring;

import java.io.IOException;

/**
 * new 一个对象的时候  有一个中间状态
 *
 * 创建对象的过程，指令重排序
 * this对象溢出
 *
 */
public class T03_ThisEscape {

    private int num = 8;

    public T03_ThisEscape(){
        new Thread(() -> System.out.println(this.num)
        ).start();
    }

    public static void main(String[] args) throws IOException {
        new T03_ThisEscape();
        System.in.read();
    }


}
