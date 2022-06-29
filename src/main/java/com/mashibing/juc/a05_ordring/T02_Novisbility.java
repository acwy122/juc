package com.mashibing.juc.a05_ordring;


/**
 *
 * 两个问题
 *
 * 可见性：由于mesi的一致性原则，可能在ready=true的时候，马上就结束线程
 * //加volatile关键字  添加可见性
 *
 * 有序性：number有可能打印 0 ，两个会换顺序
 */
public class T02_Novisbility {

    private static boolean ready = false;//加volatile关键字  添加可见性
    private static int number;

    private static class ReaderThread extends Thread{

        @Override
        public void run(){
            while (!ready){
                Thread.yield();
            }
            System.out.print(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new ReaderThread();
        t.start();
        number = 42;
        ready = true;
        t.join();
    }

}
