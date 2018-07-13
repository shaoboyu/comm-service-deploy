package com.deploy.dubbo.comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * dubbo消费者
 * @author yushaobo
 * @create 2018-07-13 22:05
 **/
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
    }
}
