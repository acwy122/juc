package com.mashibing.juc.a08_Thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T02_ThreadPollNew {

    public static void main(String[] args) {
        //核心线程数   最大线程数   存活时间   时间单位  任务队列（阻塞队列）
        ThreadPoolExecutor threadPollExecutor = new ThreadPoolExecutor(1,2,60L, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>(5));




    }
}
