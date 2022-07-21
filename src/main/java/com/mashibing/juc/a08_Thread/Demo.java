package com.mashibing.juc.a08_Thread;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Integer>> callableArrayList = Lists.newArrayList(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("1");
                return null;
            }
        }, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                System.out.println("2");
                return null;
            }
        });
        threadPool.invokeAll(callableArrayList);

    }
}
