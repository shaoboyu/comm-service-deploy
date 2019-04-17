package com.deploy.dubbo.comsumer.controller;

import com.demo.dubbo.User;
import com.deploy.dubbo.comsumer.service.CityDubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yushaobo
 * @create 2018-07-14 15:17
 **/
@RestController
public class HttpUserServlet {

    @Autowired
    private CityDubboConsumerService cityDubboConsumerService;

    @RequestMapping("/save")
    public Object saveUser() {
        User user = new User();
        user.setUsername("jaycekon");
        user.setPassword("jaycekong824");
        return cityDubboConsumerService.save(user);
    }
}
