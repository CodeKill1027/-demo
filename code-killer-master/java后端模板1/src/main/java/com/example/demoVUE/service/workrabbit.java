package com.example.demoVUE.service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
//一对多,平均分配
@Service
public class workrabbit {

    @RabbitListener(queuesToDeclare = @Queue("work"))
public void recvice(String message){
        System.out.println(message+"1");

}
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void recvice1(String message){
        System.out.println(message+"2");

    }

}
