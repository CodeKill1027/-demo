package com.example.demoVUE;

import com.example.demoVUE.dao.usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


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


@Autowired
usermapper usermapper;
@Autowired
RedisTemplate<String, String> redisTemplate;
@Test
public void a(){
    redisTemplate.opsForValue().set("a","Dsds");
    System.out.println(redisTemplate.opsForValue().get("a"));

}
}
