package com.mashibing.juc.a01_threadvasic;

import java.util.concurrent.locks.ReentrantLock;

public class T10_Interrupt_and_lockInterruptibly {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();

            try {
                SleepHelper.sleepSeconds(10);
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();

        SleepHelper.sleepSeconds(1);

        Thread t2 = new Thread(() -> {
            lock.lock();
            try{
                lock.lockInterruptibly();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });

        t2.start();
        SleepHelper.sleepSeconds(1);
        t2.interrupt();

    }

}
