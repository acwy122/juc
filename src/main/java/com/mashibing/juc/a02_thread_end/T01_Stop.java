package com.mashibing.juc.a02_thread_end;

import com.mashibing.juc.a01_threadvasic.SleepHelper;

public class T01_Stop {

    public static void main(String[] args) {
        Thread t = new Thread(() ->{
            while(true){
                System.out.println("go on");
                SleepHelper.sleepSeconds(1);
            }
        });

        t.start();

        SleepHelper.sleepSeconds(5);

        t.stop();//非常粗暴的停止线程,多线程的时候，会停止所有的线程
    }

}
