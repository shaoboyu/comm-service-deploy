package com.demo.builder;

/**
 * 自行车实体类
 *
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-9
 **/
public class Bike {

    private String color;

    private String body;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "color='" + color + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
