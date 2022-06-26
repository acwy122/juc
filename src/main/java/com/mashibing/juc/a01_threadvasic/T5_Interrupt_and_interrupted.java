package com.mashibing.juc.a01_threadvasic;

public class T5_Interrupt_and_interrupted {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            for (;;){
                if(Thread.interrupted()){
                    System.out.println("Thread is interrupted!");
                    System.out.println(Thread.interrupted());
                }
            }

        });

        t.start();

        SleepHelper.sleepSeconds(2);

        t.interrupt();
    }
}
