package com.mashibing.juc.a01_threadvasic;

public class T8_Interrupt_and_sync {

    private static Object o = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            synchronized (o){
                SleepHelper.sleepSeconds(10);
            }
        });
        t1.start();

        SleepHelper.sleepSeconds(1);

        Thread t2 = new Thread(() -> {
            synchronized (o){

            }
            System.out.println("t2 end!");
        });

        t2.start();

        SleepHelper.sleepSeconds(2);

        t2.interrupt();
    }



}
