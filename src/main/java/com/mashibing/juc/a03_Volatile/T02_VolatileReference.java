package com.mashibing.juc.a03_Volatile;

import com.mashibing.juc.a01_threadvasic.SleepHelper;

/**
 * 修饰的  引用类型（包括数组）  只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class T02_VolatileReference {

    private static class A {
        boolean running = true;
        void m(){
            System.out.println("m start!");
            while (running){}
            System.out.println("m end !");
        }
    }

    private volatile static A o = new A();

    public static void main(String[] args) {
        new Thread(o::m,"t1").start();
        SleepHelper.sleepSeconds(1);
        o.running = false;
    }

    /**
     * 三级缓存：
     *
     * registers <1ns
     *
     * L1  cache   约1ns
     *
     * L2  cache   约3ns
     *
     * L3  cache   约15ns
     *
     * main  memory   约80ns
     *
     */


}
