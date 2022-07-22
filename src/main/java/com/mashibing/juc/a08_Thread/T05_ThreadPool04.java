package com.mashibing.juc.a08_Thread;

import java.util.concurrent.Semaphore;

public class T05_ThreadPool04 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit");
        }).start();
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit");
        }).start();
        Thread.sleep(1000);
        semaphore.release();
    }
}
