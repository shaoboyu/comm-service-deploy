package com.undertow.servlet.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: shaoboyu@hotmail.com
 * @create: 19-4-22
 **/
@Component
public class TestKafkaConsumer {

//    @KafkaListener(topics = "bill-group-log")
//    public void listen(ConsumerRecord<?, ?> record){
//        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
//    }
}
