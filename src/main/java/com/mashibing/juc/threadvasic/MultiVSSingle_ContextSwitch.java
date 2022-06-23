package com.mashibing.juc.threadvasic;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MultiVSSingle_ContextSwitch {

    private static double[] nums = new double[1_0000_0000];
    private static Random r = new Random();
    private static DecimalFormat df = new DecimalFormat("0.00");

    static {
        for (int i = 0;i<nums.length;i++){
            nums[i] = r.nextDouble();
        }
    }

    /**
     * 非线程计算
     */
    public static void m1() {
        long start = System.currentTimeMillis();

        double result = 0.0;
        for (int i = 0;i < nums.length;i++){
            result += nums[i];
        }

        long end = System.currentTimeMillis();

        System.out.println("ml:"+ (end - start) +" result:"+df.format(result));
    }
    //===========================================

    static double result = 0.0;
    static double result1 = 0.0;
    static double resule2 = 0.0;

    /**
     * 两个线程计算
     * @throws InterruptedException
     */
    private static void m2() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0;i<nums.length/2;i++){
                result1 += nums[i];
            }
        });

        Thread t2 = new Thread(() -> {
           for (int i = nums.length/2;i<nums.length;i++){
               resule2 += nums[i];
           }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        result = result1 + resule2;
        long end = System.currentTimeMillis();
        System.out.println("m2:"+(end-start)+" result:"+df.format(result));

    }

    //===========================================

    /**
     * 一万个线程进行计算
     *
     * 线程并不是越大越好
     */
    private static void m3() throws InterruptedException {

        final int thresdCount = 10000;
        Thread[] threads = new Thread[thresdCount];
        double[] results = new double[thresdCount];
        final int segmentCount = nums.length / thresdCount;
        CountDownLatch latch = new CountDownLatch(thresdCount);


        for (int i=0;i<thresdCount;i++){
            int m = i;
            threads[i] = new Thread(() ->{
               for (int j=m*segmentCount;j<(m+1)*segmentCount && j<nums.length;j++){
                   results[m] += nums[j];
               }
            });

            latch.countDown();
        }

        double result3 = 0.0;

        long start = System.currentTimeMillis();
        for (Thread t:threads){
            t.start();
        }
        latch.await();

        for (int i=0;i<results.length;i++){
            result3 += results[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("m3:"+(end-start)+" result:"+df.format(result3));


    }



    public static void main(String[] args) throws InterruptedException {
        m1();
        m2();
        m3();
    }

}
