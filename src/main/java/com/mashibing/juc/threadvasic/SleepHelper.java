package com.mashibing.juc.threadvasic;

import java.util.concurrent.TimeUnit;

public class SleepHelper {

    public static void sleepSeconds(int seconds){

        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
