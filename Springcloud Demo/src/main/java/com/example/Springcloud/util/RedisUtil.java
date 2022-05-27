package com.example.Springcloud.util;


import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * redis tools
 *
 * @author yzj
 * &#064;date  2022/05/05
 * @date 2022/05/16
 */
@Service
public class RedisUtil {

    Logger logger = Logger.getLogger(RedisUtil.class);
    @Resource
    private  RedisTemplate<String,Object> redisTemplate;

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("写入redis数据失败!!!"+"key:"+key+"  value"+value);
            throw new BaseBusinessException(-1,"写入redis数据失败!!!"+"key:"+key+"  value"+value);
        }
        return result;
    }

    /**
     * 写入缓存设置时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean setEx(final String key, Object value, long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            result = true;
        } catch (Exception e) {
            logger.error("写入redis数据并且设置时间失败!!!"+"key:"+key+"  value"+value);
            throw new BaseBusinessException(-1,"写入redis数据并且设置时间失败!!!"+"key:"+key+"  value"+value);
        }
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public boolean remove( String key) {
        if (exists(key)) {
            try {
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 使用redis执行lua执行
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                redisScript.setScriptText(script);
                redisScript.setResultType(Long.class);
                // 第一个要是script 脚本 ，第二个需要判断的key，第三个就是key所对应的值。
                Long execute = redisTemplate.execute(redisScript, Collections.singletonList(key),redisTemplate.opsForValue().get(key) );
                return true;
            }catch ( Exception e){
                logger.error("删除redis失败!!!"+key);
                throw new BaseBusinessException(-1,"删除redis失败!!!"+"key:"+key );
            }
        }
        return false;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        boolean result = false;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if (Objects.nonNull(operations.get(key))) {
            result = true;
        }
        return result;
    }


}

