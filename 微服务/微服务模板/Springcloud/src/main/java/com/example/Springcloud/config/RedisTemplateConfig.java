package com.example.Springcloud.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisTemplateConfig {
    //master

    @Value("${spring.redis.master.host}")
    private String masterHost;
    @Value("${spring.redis.master.port}")
    private String masterPort;
    @Value("${spring.redis.master.password}")
    private String masterPassword;
    @Value("${spring.redis.master.database}")
    private int masterIndex;
    //salver1

    @Value("${spring.redis.salver1.host}")
    private String salve1Host;
    @Value("${spring.redis.salver1.port}")
    private String salve1Port;
    @Value("${spring.redis.salver1.password}")
    private String salver1Password;
    @Value("${spring.redis.salver1.database}")
    private int salver1Index;
    //salver2

    @Value("${spring.redis.salver2.host}")
    private String salve2Host;
    @Value("${spring.redis.salver2.port}")
    private String salve2Port;
    @Value("${spring.redis.salver2.password}")
    private String salver2Password;
    @Value("${spring.redis.salver2.database}")
    private int salver2Index;


    @Value("${spring.redis.total.MAX_IDLE}")
    private  int MAX_IDLE;
    //最大空闲连接数

    @Value("${spring.redis.total.MAX_TOTAL}")
    private  int MAX_TOTAL ;
    //最大连接数

    @Value("${spring.redis.total.MAX_WAIT_MILLIS}")
    private  long MAX_WAIT_MILLIS ;
    //建立连接最长等待时间


    //配置工厂
    public RedisConnectionFactory connectionFactory(String host, int port, String password, int maxIdle,
                                                    int maxTotal, long maxWaitMillis, int index) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        if (!StringUtils.isEmpty(password)) {
            jedisConnectionFactory.setPassword(password);
        }
        if (index != 0) {
            jedisConnectionFactory.setDatabase(index);
        }
        jedisConnectionFactory.setPoolConfig(poolConfig(maxIdle, maxTotal, maxWaitMillis, false));
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }


    //连接池配置
    public JedisPoolConfig poolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }



    @Bean(name = "redisMasterTemplate")
    public StringRedisTemplate redisMasterTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(masterHost, Integer.parseInt(masterPort), masterPassword, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, masterIndex));
        return template;
    }


    @Bean(name = "redisSalver1Template")
    public StringRedisTemplate userSalver1Template() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(salve1Host, Integer.parseInt(salve1Port), salver1Password, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, salver1Index));
        return template;
    }

    @Bean(name = "redisSalver2Template")
    public StringRedisTemplate userSalver2Template() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(
                connectionFactory(salve2Host, Integer.parseInt(salve2Port), salver2Password, MAX_IDLE, MAX_TOTAL, MAX_WAIT_MILLIS, salver2Index));
        return template;
    }

    public void redisSerializeConfig(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        redisSerializer.setObjectMapper(om);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(redisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(redisSerializer);
        //template.afterPropertiesSet();
    }

}