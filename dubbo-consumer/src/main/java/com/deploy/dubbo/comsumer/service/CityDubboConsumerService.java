package com.deploy.dubbo.comsumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.dubbo.User;
import com.demo.dubbo.UserService;
import org.springframework.stereotype.Component;

/**
 * @author yushaobo
 * @create 2018-07-14 15:14
 **/
@Component
public class CityDubboConsumerService {

    @Reference
    private UserService userService;

    public boolean save(User user){

        User user1 = userService.saveUser(user);
        if (user!=null){
            return true;
        }
        return false;
    }
}
