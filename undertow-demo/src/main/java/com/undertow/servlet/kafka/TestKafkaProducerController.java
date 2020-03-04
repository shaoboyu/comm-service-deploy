package com.undertow.servlet.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-22
 **/
@RestController
@RequestMapping("/test/kafka/producer")
public class TestKafkaProducerController {
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;

//    @RequestMapping("/send")
//    public String send(String msg){
//        ListenableFuture<SendResult<String, String>> test_topic = kafkaTemplate.send("bill-group-log", msg);
//        return "success";
//    }



}
