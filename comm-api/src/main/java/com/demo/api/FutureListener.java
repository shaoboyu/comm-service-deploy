package com.demo.api;

/**
 * 创建自己的监听
 * 1.当前线程执行完毕或超时是会触发该事件
 *
 * @author: yushaobo
 * @create: 19-1-25
 **/
public interface FutureListener<V> {
    void run(Result<V> result);
}
