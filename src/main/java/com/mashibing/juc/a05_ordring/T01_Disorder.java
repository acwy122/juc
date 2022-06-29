package com.mashibing.juc.a05_ordring;

import java.util.concurrent.CountDownLatch;


/**
 * 程序真的是按照顺序执行的吗
 *
 *
 * 第二条指令先于第一条指令执行完成
 *
 * 在两条指令中无依赖关系
 *
 * 乱序存在的条件：
 * 不影响单线程的最终一致性
 * as -- if -- serial
 *
 */
public class T01_Disorder {

    private static int x = 0,y=0;
    private static int a = 0,b=0;

    public static void main(String[] args) throws InterruptedException {

        for (long i = 0;i < Long.MAX_VALUE; i++ ){
            x=0;
            y=0;
            a=0;
            b=0;

            CountDownLatch latch = new CountDownLatch(2);

            Thread one  = new Thread(() -> {
                public void run {
                    a=1;
                    x=b;

                    latch.countDown();
                }
            });

            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b=1;
                    y=a;

                    latch.countDown();
                }
            });

            one.start();
            other.start();
            latch.await();
            String result = "第"+ i + "次("+x+","+y+")";

            if(x==0 && y==0){
                System.out.println(result);
                break;
            }

        }

    }

}
