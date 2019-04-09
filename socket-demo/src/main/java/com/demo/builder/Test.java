package com.demo.builder;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public class Test {
    public static void main(String[] args) {
        Director director = new Director(new MobikeBuilder());
        Bike construct = director.construct();
        System.out.println(construct);
    }
}
