package com.mashibing.juc.threadvasic;

import java.util.concurrent.*;

public class HowToCreateThread {

    /**
     * 1、继承Thread 重写run
     */
    static class MyThread extends Thread{
        @Override
        public void run(){ System.out.print("Hello MyThread");}
    }

    /**
     * 2、实现Runnable接口
     * 重写run方法
     * 推荐
     */
    static class MyRun implements Runnable{
        @Override
        public void run(){ System.out.print("Hello MyRun!");}
    }

    /**
     * 带返回值的任务执行
     *
     * 线程池
     */
    static class MyCall implements Callable<String>{
        @Override
        public String call(){
            System.out.print("Hello MyCall");
            return "success";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new MyThread().start();
        new Thread(new MyRun()).start();
        //3、lambda表达式创建线程
        new Thread(() -> {
            System.out.println("Hello lambda");
        }).start();

        //创建线程池,用线程池创建线程
        FutureTask<String> task = new FutureTask<>(new MyCall());
        Thread t = new Thread(task);
        t.start();
        System.out.println(task.get());
        /*Thread t = new Thread(new FutureTask<String>(new MyCall()));
        t.start();*/


        //Callable最好用线程池执行
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {//执行的方法扔给线程池
            System.out.println("hello ThreadPool");
        });
        Future<String> f = service.submit(new MyCall());//异步线程，把返回值放回Future中
        String s = f.get();//get会造成线程阻塞
        System.out.println(s);
        service.shutdown();//关闭

    }



}
