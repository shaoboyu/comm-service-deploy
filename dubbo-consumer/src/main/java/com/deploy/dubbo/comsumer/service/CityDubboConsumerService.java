package com.deploy.dubbo.comsumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.deploy.dubbo.comsumer.domain.User;
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
