package com.example.demoVUE.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class dotailuyou {
   @RabbitListener(bindings = {
           @QueueBinding(
                   value = @Queue,
                   exchange = @Exchange(type = "topic",name = "topics"),
                   key = {"user.save"}
           )
   })
    public void revice(String message){
       System.out.println(message);
    }
}
