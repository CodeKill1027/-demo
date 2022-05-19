package com.example.demo;



import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static java.lang.Thread.sleep;
import static org.apache.tomcat.jni.Thread.*;


@SpringBootTest
class DemoApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;





      Logger logger = Logger.getLogger(DemoApplicationTests.class);

    @Test
    void contextLoads() throws InterruptedException {

   logger.info("你好");

    }
    @Test
    void contextLoads1() {
        //编码格式有 UTF-8 GB2312 GBK UTF-16 UTF-32
        int length = "hello你好!@@".getBytes(StandardCharsets.UTF_8).length;
        System.out.println(length);
    }




}
