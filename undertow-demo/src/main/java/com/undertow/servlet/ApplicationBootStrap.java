package com.undertow.servlet;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-17
 **/
@SpringBootApplication
@EnableDubboConfiguration
public class ApplicationBootStrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApplicationBootStrap.class, args);
    }
}
