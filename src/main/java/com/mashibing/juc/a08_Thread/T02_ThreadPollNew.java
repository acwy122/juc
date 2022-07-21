package com.mashibing.juc.a08_Thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T02_ThreadPollNew {

    public static void main(String[] args) {
        int cps = 1;
        int mps = 2;
        int c = 5;
        int size = mps + c;

        //核心线程数   最大线程数   存活时间   时间单位  任务队列（阻塞队列）  线程工厂  与策略
        ThreadPoolExecutor threadPollExecutor = new ThreadPoolExecutor(1,2,60L, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>(c), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

        for(int i=0;i<size;i++){
            threadPollExecutor.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPollExecutor.execute(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("wait");
    }
}
