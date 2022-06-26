package com.mashibing.juc.a03_Volatile;

import com.mashibing.juc.a01_threadvasic.SleepHelper;


/**
 * 线程的可见性
 *
 * volatile  保持线程的可见性
 *
 * volatile修饰的内存，每次读取都是从内存重新读取
 *
 * 修饰的  引用类型（包括数组）  只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class T01_HelloVolatile {

    //private static /*volatile*/ boolean running = true;
    private static volatile boolean running = true;
    public static void m() {
        System.out.println("m start");

        while (running){
            System.out.println("hello");//没有volatile  也可出发线程的可见性  因为System.out.println这里有一个synchonized,效率会变低
        }

        System.out.println("m end!");

    }

    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();

        new Thread(T01_HelloVolatile::m,"t1").start();//lambda表达式启用线程

        SleepHelper.sleepSeconds(1);

        running = false;
    }


}
