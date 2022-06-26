package com.mashibing.juc.threadvasic;

import javax.management.ObjectName;

public class T7_Interrupt_and_wait {

    private static Object o = new Object();

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            synchronized (o){
                try {
                    o.wait();
                } catch (InterruptedException e) {//sleep  方法会抛出异常，记住
                    System.out.println("Thread is interrupted!");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }

        });

        t.start();

        SleepHelper.sleepSeconds(5);

        t.interrupt();
    }
}
