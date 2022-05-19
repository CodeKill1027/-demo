package com.example.demoVUE.service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author yzj
 */
//一对一
@Service
@RabbitListener(queuesToDeclare = @Queue(value = "hello")) //监听队列
public class rabbit {
    @RabbitHandler
    public void recive(String message){
        for (int i=0;i<100;i++){
            System.out.println(message);
        }
    }
}
