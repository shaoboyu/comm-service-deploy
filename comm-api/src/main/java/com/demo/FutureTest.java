package com.demo;

import com.demo.api.Future;
import com.demo.api.FutureListener;
import com.demo.api.Result;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yushaobo
 * @create 2019-09-23 16:49
 **/
public class FutureTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("ScheduledTask");
            }
        },10, TimeUnit.SECONDS);
    }
}
