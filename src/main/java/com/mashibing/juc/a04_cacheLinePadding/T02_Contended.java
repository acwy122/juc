package com.mashibing.juc.a04_cacheLinePadding;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

public class T02_Contended {

    public static long COUNT = 10_0000_0000L;

    private static class T{//增加无意义的值之后，效率提升了
        @Contended//1.8之后增加的注解，需要在jvm运行参数中增加-XX:-RestrictContended，打开之后才能生效，到1.9之后，就不起作用了
        public long x = 0L;
    }

    public static T02_Contended.T[] arr = new T02_Contended.T[2];

    static {
        arr[0] = new T02_Contended.T();
        arr[1] = new T02_Contended.T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (long i = 0;i<COUNT;i++){
                arr[0].x = i;
            }

            latch.countDown();
        });

        Thread t2 = new Thread(() ->{
            for (long i=0;i<COUNT;i++){
                arr[1].x = i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();

        t1.start();
        t2.start();

        latch.await();
        System.out.println((System.nanoTime() - start)/100_0000);
    }

}
