package com.demo.builder;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public class MobikeBuilder extends BikeBuilder {

    Bike bike = new Bike();

    @Override
    void createColor() {
        this.bike.setColor("yellow");
    }

    @Override
    void createBody() {
        this.bike.setBody("Mobike");
    }

    @Override
    Bike buildBike() {
        return this.bike;
    }
}
