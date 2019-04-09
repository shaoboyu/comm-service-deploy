package com.demo.builder;

/**
 * 建造者模式-抽象接口
 *
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public abstract class BikeBuilder {

    abstract void createColor();

    abstract void createBody();

    abstract Bike buildBike();

}
