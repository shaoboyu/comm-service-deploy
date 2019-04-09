package com.demo.builder.build;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public class Test {

    public static void main(String[] args) {

        NewComputer build = new NewComputer.Builder()
                .cpu("i7")
                .mainboard("ausu")
                .memory("32G")
                .screen("scree")
                .build();
        System.out.println(build);
    }
}
