package com.example.Springcloud.service;

import org.apache.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redissonlock
 *
 * @author yzj
 * @date 2022/05/04
 */
@Service

public class Redissonlock {
    @Resource
    Redisson redisson;
    @Resource
  private  RedisTemplate<String,Object> redisTemplate;
    Logger logger = Logger.getLogger(Redissonlock.class);
    public String redislock(){
        //获取锁
        RLock redissonLock = redisson.getLock("lock");
        try{
            redissonLock.lock(30, TimeUnit.SECONDS);

            Integer goods =(Integer) redisTemplate.opsForValue().get("goods");
            if (goods>= 1){
                goods--;
               redisTemplate.opsForValue().decrement("goods");
               logger.debug("所剩商品数量:"+goods);
                return "所剩商品数量:"+goods;
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        finally{
            //释放锁
            redissonLock.unlock();
        }
        return "商品已卖完!!!!";
    }
}

