package com.mashibing.juc.a02_thread_end;

import com.mashibing.juc.a01_threadvasic.SleepHelper;

public class T02_Suspend_Resume {

    public static void main(String[] args) {
        Thread t = new Thread(() ->{
            while(true){
                System.out.println("go on");
                SleepHelper.sleepSeconds(1);
            }
        });

        t.start();

        SleepHelper.sleepSeconds(5);

        t.suspend();//非常粗暴的暂停线程,多线程的时候，会停止所有的线程，会产生思索

        SleepHelper.sleepSeconds(3);

        t.resume();//非常粗暴的恢复线程,多线程的时候，会恢复所有的线程
    }
}
