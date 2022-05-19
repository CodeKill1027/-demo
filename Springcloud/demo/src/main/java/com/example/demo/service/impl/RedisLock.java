package com.example.demo.service.impl;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * 复述,锁
 *
 * @author yzj
 * @date 2022/04/14
 */
public class RedisLock {

    @Resource(name = "redisMasterTemplate")
    StringRedisTemplate stringRedisTemplate;
    public  void test() throws InterruptedException {
        Integer a = 0;

            String s = UUID.randomUUID().toString(); //通过唯一的ID来解决误删问题
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent("say", s, Duration.ofSeconds(160));
            if (Boolean.TRUE.equals(aBoolean)) {
                a++;
                System.out.println(a);
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 使用redis执行lua执行
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                redisScript.setScriptText(script);
                redisScript.setResultType(Long.class);
                // 第一个要是script 脚本 ，第二个需要判断的key，第三个就是key所对应的值。
                Long execute = stringRedisTemplate.execute(redisScript, Collections.singletonList("say"), s);
            } else {
                System.out.println("该锁没有释放!!!!");
                // 其他线程等待
                try {
                    // 睡眠
                    Thread.sleep(1000);
                    // 睡醒了之后，调用方法。
                    test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }


    public static void main(String[] args) throws InterruptedException {
        RedisLock redisLock = new RedisLock();
        redisLock.test();

    }
}