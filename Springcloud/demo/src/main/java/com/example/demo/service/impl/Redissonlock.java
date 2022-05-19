package com.example.demo.service.impl;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yzj
 */
public class Redissonlock {
    @Resource
    Redisson redisson;
    @Resource(name = "redisMasterTemplate")
    StringRedisTemplate stringRedisTemplate;
    public void redislock(){
        //获取锁
        RLock redissonLock = redisson.getLock("锁名");
        try{
            redissonLock.lock(30, TimeUnit.SECONDS);
            //执行业务逻辑
        } finally{
            //释放锁
            redissonLock.unlock();
        }
    }

}

