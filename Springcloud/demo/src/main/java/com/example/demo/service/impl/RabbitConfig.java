package com.example.demo.service.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.Channel;
import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 兔子配置
 *
 * @author yzj
 * @date 2022/04/13
 */
@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = {RabbitmqName.BOOT_QUEUE})
    public void handle(Channel channel, Message message) {
        //获取消息编号
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //消息消费的代码写到这里  自定义异常
//            int a[] = new int[2];
//            System.out.println(a[3]);
            String s = new String(message.getBody());
            System.out.println("s = " + s);
            //消费完成后，手动 ack

            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            //手动 nack丢弃这条消息
            try {
                channel.basicNack(deliveryTag, false, false); //消费被拒，加入死信队列  第二个参数是不是重新放入队列
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息成功到达交换器");
        }else{
            System.out.println("消息发送失败***错误原因"+cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.print("消息未成功路由到队列:");
        System.out.println(returned);
    }
}
