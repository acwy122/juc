package com.mashibing.juc.threadvasic;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * java的线程状态
 *
 * 1\new : 线程刚刚创建，还没有启动
 * 2\runnable: 可运行状态，由线程调度可以安排执行：包括两个  一个running    一个ready   thread.yieid
 *
 * 4\waiting：等待背唤醒
 * 5\timed waiting:隔一段时间够自动唤醒
 *
 * 6\blocked：被阻塞 正在等待锁   只有被synchronized锁住了  才是blocked状态
 * 3\terminated：线程结束
 *
 *
 */
public class T3_ThreadState {

    public static void main(String[] args) throws InterruptedException {
        //获取线程的三种状态
        Thread t1 = new Thread(() ->{
            System.out.println("2:" + Thread.currentThread().getState());
            for (int i=0;i<3;i++){
                SleepHelper.sleepSeconds(1);
                System.out.println(i+"  ");
            }
        });
        System.out.println("1:" + t1.getState());

        t1.start();
        t1.join();
        System.out.println("3:" + t1.getState());

        //=======================================

        Thread t2 = new Thread(()->{
            try {
                LockSupport.park();
                System.out.println("t2 go no!");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("4:" + t2.getState());

        LockSupport.unpark(t2);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("5: "+ t2.getState());


        //====================================================

        final Object o = new Object();
        Thread t3 = new Thread(() -> {
           synchronized (o){
               System.out.println("t3得到了   o");
           }
        });

        new Thread(() -> {
            synchronized (o){
                SleepHelper.sleepSeconds(5);
            }
        }).start();

        SleepHelper.sleepSeconds(1);

        t3.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("6: " + t3.getState());

        //===========================================

        final Lock lock = new ReentrantLock();//juc的锁  用cas实现   锁是waiting状态
        Thread t4 = new Thread(() -> {
            lock.lock();//省略try  finally
            System.out.println("t4得到了锁 0");
            lock.unlock();
        });

        new Thread(() -> {
            lock.lock();
            SleepHelper.sleepSeconds(5);
            lock.unlock();
        }).start();

        SleepHelper.sleepSeconds(1);
        t4.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("7:" + t4.getState());

        //============================================

        Thread t5 = new Thread(() ->{//锁是  waiting状态
            LockSupport.park();
        });

        t5.start();

        SleepHelper.sleepSeconds(5);

        System.out.println("t5" + t5.getState());

        LockSupport.unpark(t5);

    }


}
