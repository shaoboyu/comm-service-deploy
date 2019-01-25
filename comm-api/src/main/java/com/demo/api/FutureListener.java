package com.demo.api;

/**
 * @author: yushaobo
 * @create: 19-1-25
 **/
public interface FutureListener<V> {
    void run(Result<V> result);
}
