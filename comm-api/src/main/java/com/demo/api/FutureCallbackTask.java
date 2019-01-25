package com.demo.api;

/**
 * @author: yushaobo
 * @create: 19-1-25
 **/
public interface FutureCallbackTask extends Runnable {
    Future<?> getFuture();
}