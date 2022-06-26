package com.mashibing.juc.threadvasic;

/**
 *
 * 线程的打断
 * 三种方法
 *
 * interrupt()   打断某个线程（设置标志位）
 *
 * isInterrupted()   查询某个线程是否被打断过（查询标志位）
 *
 * static interrunted()   查询当前线程是否被打断过，并重置打断标志
 *
 *
 */

public class T4_Interrupt_and_isInterrupted {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for(;;){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("thread is interrupted!");
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });

        t.start();
        SleepHelper.sleepSeconds(2);

        t.interrupt();


    }


}
