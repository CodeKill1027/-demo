package com.example.Springcloud.service.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author yzj
 */
@Service
public class Mqservice {
    @Autowired
  private RabbitTemplate rabbitTemplate;


    public void sendGoodsToMq(){

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             *
             * @param correlationData 相关配置信息
             * @param ack 交换机是否成功收到消息
             * @param cause 错误信息
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack){
                    System.out.println("消息成功发送");
                    System.out.println(correlationData);
                }else {
                    System.out.println("错误原因"+cause);
                }
            }});

        for (int i=0;i<5;i++) {
            rabbitTemplate.convertAndSend("hello","", Integer.toString(i));
        }
    }


    public void send(){

        rabbitTemplate.setMandatory(true);
        //设置交换机处理失败消息的模式
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {

            /**
             * 返回消息
             *
             * @param returnedMessage 返回消息
             */
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println(returnedMessage);
            }

        });
        rabbitTemplate.convertAndSend("direct", "info", "什么鬼");

    }

    

    @RabbitListener(queuesToDeclare =@Queue("hello"))
    public  void a1( String a){

        System.out.println(a+"号");
    }
    @RabbitListener(queuesToDeclare =@Queue("hello"))
    public  void a( String a){

        System.out.println(a+"号");
    }
    @RabbitListener(queuesToDeclare =@Queue("hello"))
    public  void a2( String a){

        System.out.println(a+"号");
    }



    @RabbitListener(bindings = {@QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))})
    public void recive(String message){
        System.out.println(message);
    }
    @RabbitListener(bindings = {@QueueBinding(value = @Queue,exchange = @Exchange(value = "logs",type = "fanout"))})
    public void recive1(String message){
        System.out.println(message);
    }



    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "direct",type ="direct" ), key = {"info","a"})})
    public void recvice12(String message){
        System.out.println(message);
    }
    @RabbitListener(bindings = {@QueueBinding(value = @Queue, exchange = @Exchange(value = "direct",type ="direct" ), key = {"info"})})
    public void recvice1223(String message){
        System.out.println(message);
    }



}
