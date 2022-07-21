package com.mashibing.juc.a07_atomicXXX;

import java.util.concurrent.CountDownLatch;


//这个应该是悲观锁的概念
public class T02_IPlusPlus {

    private static long n = 0L;

    public static void main(String [] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i=0;i<threads.length;i++){
            threads[i] = new Thread(() -> {
               for(int j=0;j<10000;j++){
//                   synchronized (T02_IPlusPlus.class){
                       n++;
//                   }
               }
            });
        }

        for(Thread t:threads){
            t.start();
        }

        latch.await();

        System.out.println(n);
    }

    /**
     * synchronized与三大特性
     */
}
