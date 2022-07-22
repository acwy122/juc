package com.mashibing.juc.a08_Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 普通的线程安全写法
 *
 * 与cas写法
 */
public class T05_ThreadPool05 {

    public static int a = 0;

    public static synchronized void inc(){
        a++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<1000000;i++){
            executorService.execute(()->inc());
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(a);
    }
}
