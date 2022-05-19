package com.example.Springcloud;


import com.alibaba.fastjson.JSON;
import com.example.Springcloud.dao.usermapper;
import com.example.Springcloud.pojo.Role;
import com.example.Springcloud.service.Redissonlock;
import com.example.Springcloud.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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
  Logger logger = Logger.getLogger(RedisUtil.class);


  @Test
  public void a()  {

    ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();



  }


  public static void main(String[] args) {}

}
