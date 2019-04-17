package com.undertow.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-17
 **/
@SpringBootApplication
//@EnableDubboConfiguration
public class ApplicationBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootStrap.class, args);
    }
}
