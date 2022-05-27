package com.example.Springcloud;
import com.example.Springcloud.dao.usermapper;
import com.example.Springcloud.service.Aop123;
import com.example.Springcloud.service.Redissonlock;
import com.example.Springcloud.util.RedisUtil;
import com.example.demo.service.HzyService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
@SpringBootTest
class SpringcloudApplicationTests {
@Autowired
usermapper usermapper;
  @Resource
  RabbitTemplate rabbitTemplate;
  @Resource
  RedisTemplate<String,Object> redisTemplate;
  @Autowired
  Redissonlock redissonlock;
  static Logger logger = Logger.getLogger(RedisUtil.class);

  @Autowired
  Aop123 aop123 ;

  @Autowired
  HzyService hzyService;

  @Test
  public void a(){
    System.out.println(hzyService.getHelloWorld());
  }

}
