package com.demo.builder.build;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public class Computer {
    private String cpu;
    private String screen;
    private String memory;
    private String mainboard;

    public String getCpu() {
        return cpu;
    }

    public Computer(String cpu, String screen, String memory, String mainboard) {
        this.cpu = cpu;
        this.screen = screen;
        this.memory = memory;
        this.mainboard = mainboard;
    }
}
