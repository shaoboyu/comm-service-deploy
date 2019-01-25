package com.demo.api;

/**
 * @author: yushaobo
 * @create: 19-1-25
 **/
public interface EventHandler<E> {
    void run(Object sender, E e);
}