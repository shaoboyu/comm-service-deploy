package com.demo.aop.proxy;

/**
 * @author yushaobo
 * @create 2018-09-02 16:03
 **/
public class Business implements IBusiness,IBusiness2 {
    @Override
    public boolean doSomeThing() {
        System.out.println("执行业务逻辑");
        return true;
    }

    @Override
    public void doSomeThing2() {
        System.out.println("执行业务逻辑2");
    }
}
