package com.mashibing.juc.a01_threadvasic;

public class T6_Interrupt_and_sleep {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {//sleep  方法会抛出异常，记住
                System.out.println("Thread is interrupted!");
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t.start();

        SleepHelper.sleepSeconds(5);

        t.interrupt();
    }

}
