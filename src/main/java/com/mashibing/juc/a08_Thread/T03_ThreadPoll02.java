package com.mashibing.juc.a08_Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * 状态之间的转换：
 * Running  -> Shutdown:调用shutdown()方法
 * (Running or shutdown) -> stop:调用shutdownNow()方法
 * Shutdown -> tidying：队列和线程池都是空的
 *
 * stop -> TIDYING:线程池为空
 * Tidying -> Terminated:钩子函数terminated()执行完成
 *
 */
public class T03_ThreadPoll02 {

    public static void main(String[] args) throws InterruptedException {
        //背压 MQ RxJava  、  react Streaming webflux

        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MINUTES, new LinkedBlockingDeque<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if(!e.isShutdown()){
                    r.run();
                    System.out.println("线程池坚持不住了，别往里面再方任务了");
                }
            }
        });//拒绝策略,  或者手动创建自己的策略
        for(int i=0;i<2000;i++){
            executorService.submit(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        //System.out.println(getUidList());
    }

    static List<String> getUidList() throws InterruptedException {
        List<String> uIdList = new ArrayList<>();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        });

        executorService.submit(()->{
            System.out.println("hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            uIdList.add("1");
            System.out.println("exit");
        });
        executorService.shutdown();//关闭线程
        //executorService.shutdownNow();//想要马上关闭
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        return uIdList;
    }

}