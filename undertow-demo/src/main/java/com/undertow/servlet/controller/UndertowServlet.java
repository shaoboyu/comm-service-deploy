package com.undertow.servlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-18
 **/
@Controller
@RequestMapping("/test")
public class UndertowServlet {

    @GetMapping("/user")
    @ResponseBody
    public String getUser(){
        return "hello underTow";
    }
}
