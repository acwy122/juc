package com.mashibing.juc.a02_thread_end;

import com.mashibing.juc.a01_threadvasic.SleepHelper;

public class T03_VolatileFlag {

    //用  volatile  关键字修饰的静态变量停止线程
    //相对优雅的一种停止线程的一种方式
    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
             long i = 0L;
             while (running){

               i++;
             }
            System.out.println("end and i = " + i);
        });

        t.start();

        SleepHelper.sleepSeconds(1);

        running = false;

    }


}
