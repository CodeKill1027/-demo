package com.example.Springcloud.config;


import com.example.Springcloud.service.iml.RabbitmqName;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmqconfig
 *
 * @author yzj
 * @date 2022/04/11
 */
@Configuration
public class Rabbitmqconfig {

 @Bean(RabbitmqName.BOOT_QUEUE)
 public Queue bootQueue(){
     Map<String, Object> map = new HashMap<>();
    //map.put("x-message-ttl", 10000);
     // 队列所有消息过期时间
     map.put("x-dead-letter-exchange", "deadExchange");
     //消息过期后转发到死信交换机
        //map.put("x-max-length", 10);
          //消息条数超过后转发到死信队列
    // map.put("x-max-length-bytes",15);
     //map.put("x-dead-letter-exchange", "deadExchange"); //死信交换机
     //map.put("x-dead-letter-routing-key", ""); //死信路由键
     //@Bean(RabbitmqName.BOOT_QUEUE)
     //    public Queue bootQueue1(){
     //        return new Queue(RabbitmqName.BOOT_QUEUE, true);
     //    }
//     MessagePostProcessor messagePostProcessor = new MessagePostProcessor(){
//         @Override
//         public Message postProcessMessage(Message message) throws AmqpException {
//             message.getMessageProperties().setExpiration("6000");
//             message.getMessageProperties().setContentEncoding("UTF-8");
//             return message;
//         } //messagePostProcessor作为发送消息时的第四个参数
//     }  同时设置以最短时间为主
     return new Queue(RabbitmqName.BOOT_QUEUE, true, false, false, map);
 }

//死消息交换机 1消费被拒绝 2消息过长 3消息过期
 @Bean("deadExchange")
 public Exchange deadExchange(){
     return ExchangeBuilder.fanoutExchange("deadExchange").durable(true).build();
 }
    //死消息队列
 @Bean("deadQueue")
 public Queue deadQueue(){
     return new Queue("deadQueue", true);
 }

    //绑定死消息队列到死消息交换机
    @Bean("deadBinding")
    public Binding deadBinding(@Qualifier("deadQueue") Queue deadQueue, @Qualifier("deadExchange") Exchange deadExchange){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("").noargs();
    }


  @Bean(RabbitmqName.FANOUT_EXCHANGE)
  public Exchange fanoutExchange(){
    return ExchangeBuilder.fanoutExchange(RabbitmqName.FANOUT_EXCHANGE).durable(true).build(); //广播
  }

  @Bean(RabbitmqName.DIRECT_EXCHANGE)
    public Exchange bootExchange(){
      return ExchangeBuilder.directExchange(RabbitmqName.DIRECT_EXCHANGE).durable(true).build();//路由
  }

    @Bean(RabbitmqName.TOPIC_EXCHANGE)
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(RabbitmqName.TOPIC_EXCHANGE).durable(true).build();//动态路由
    }

    @Bean
    public Binding queueBinding(@Qualifier(RabbitmqName.BOOT_QUEUE)Queue queue, @Qualifier(RabbitmqName.TOPIC_EXCHANGE) Exchange exchange){
   return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
    @Bean
    public Binding queueBinding1(@Qualifier(RabbitmqName.BOOT_QUEUE)Queue queue, @Qualifier(RabbitmqName.FANOUT_EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
    @Bean
    public Binding queueBinding2(@Qualifier(RabbitmqName.BOOT_QUEUE)Queue queue, @Qualifier(RabbitmqName.DIRECT_EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("a").noargs();
    }



}
