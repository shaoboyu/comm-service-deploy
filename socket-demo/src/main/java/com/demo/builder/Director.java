package com.demo.builder;

/**
 * 建造者模式-指挥者
 *
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public class Director {

    private BikeBuilder bikeBuilder;

    public Director(BikeBuilder bikeBuilder) {
        this.bikeBuilder = bikeBuilder;
    }

    public Bike construct(){
        bikeBuilder.createBody();
        bikeBuilder.createColor();
        return bikeBuilder.buildBike();
    }
}
