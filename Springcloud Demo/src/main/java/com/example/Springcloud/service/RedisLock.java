package com.example.Springcloud.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.UUID;

/**
 * 复述,锁
 *
 * @author yzj
 * @date 2022/04/14
 */
//@Transactional
//1私有方法不生效
//2无事务方法调用有事务不生效
//3  此外，注解@Transactional默认只针对运行时异常生效
//如果需要它生效，可以借助rollbackFor属性来指明，触发回滚的异常类型
//@Transactional(rollbackFor = Exception.class)

public class RedisLock {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    public  void test() throws InterruptedException {
        Integer a = 0;

            String s = UUID.randomUUID().toString(); //通过唯一的ID来解决误删问题
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("say", s, Duration.ofSeconds(160));
            if (Boolean.TRUE.equals(aBoolean)) {
                a++;
                System.out.println(a);
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 使用redis执行lua执行
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                redisScript.setScriptText(script);
                redisScript.setResultType(Long.class);
                // 第一个要是script 脚本 ，第二个需要判断的key，第三个就是key所对应的值。
                Long execute = redisTemplate.execute(redisScript, Collections.singletonList("say"), s);
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