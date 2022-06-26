package com.mashibing.juc.a02_thread_end;

import com.mashibing.juc.a01_threadvasic.SleepHelper;

public class T04_Interrupt_and_NormalThread {


    /**
     * 更加优雅的停止线程
     * @param args
     */
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            while (!Thread.interrupted()){
                //sleep wait
            }
            System.out.println("t1 end!");
        });

        t.start();

        SleepHelper.sleepSeconds(1);

        t.interrupt();

    }

    /**
     *
     * 并发编程三大特性
     * 必问  必备
     *
     * 可见性（visibility）
     *
     *
     *
     * 有序性（ordering）
     *
     *
     *
     * 原子性（atomicity）
     *
     *
     *
     */

}
