package com.example.demoVUE;

import com.example.demoVUE.dao.usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;


@SpringBootTest
class DemoVueApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;
@Test
public void t(){
       rabbitTemplate.convertAndSend("work","dsd4564567867896sds");
}
//work
    @Test
    public void t1(){
        for (int i=0;i<3;i++){
            rabbitTemplate.convertAndSend("work","dsd4564567867896sds");
        }
    }

    //广播
    @Test
    public void t2(){
            rabbitTemplate.convertAndSend("logs","","23231234243");
    }

    //路由
    @Test
    public void t3(){
        rabbitTemplate.convertAndSend("direct","info","23info43");
    }
    //动态路由/订阅模式
    @Test
    public void t4(){
        rabbitTemplate.convertAndSend("topics","user.save","topices");
    }

    @Resource(name = "redisOrderTemplate")
    StringRedisTemplate redisOrderTemplate1;

    @Resource(name = "redisUserTemplate")
    StringRedisTemplate redisUserTemplate2;
    @Test
    public void a(){
    redisOrderTemplate1.opsForValue().set("name","Dsdsd");
        Object name = redisOrderTemplate1.opsForValue().get("name");
        System.out.println(name);

    }


}
