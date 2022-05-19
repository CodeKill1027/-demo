package com.example.demoVUE.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class guangbo {
     @RabbitListener(bindings = {
             @QueueBinding(
                     value = @Queue,
                     exchange = @Exchange(value = "logs",type = "fanout")
             )
     })
    public void recive(String message){
         System.out.println(message);
    }
}
