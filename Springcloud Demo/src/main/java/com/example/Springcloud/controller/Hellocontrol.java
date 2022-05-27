package com.example.Springcloud.controller;

import com.example.Springcloud.dao.usermapper;
import com.example.Springcloud.pojo.Role;

import com.example.Springcloud.service.Aop123;
import com.example.Springcloud.service.Redissonlock;
import com.example.Springcloud.util.RedisUtil;
import com.example.Springcloud.util.ResultResponse;
import org.apache.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yzj
 */
@RestController
@RequestMapping("/SpringSource")
public class Hellocontrol {
    @Resource
    RedisUtil redisUtil;
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    Redissonlock redissonlock;
    Logger logger = Logger.getLogger(Hellocontrol.class);
    @Resource
    usermapper usermapper;
    @Value("${server.port}")
    private String port;
    @Resource
    Redisson redisson;
    @GetMapping("/open")
    @Transactional(rollbackFor = Exception.class)
    public ResultResponse open(@RequestParam("username") String username)  {
        Random random = new Random();
        ResultResponse resultResponse = new ResultResponse();
        Object o = redisUtil.get(username);
        if (o ==null){
            //缓存没有数据 使用分布式锁防止缓存击穿
            //获取锁
            RLock redissonLock = redisson.getLock("lock");
//            redisson.getFairLock("公平锁") 按线程顺序执行
            try{
                redissonLock.lock(120, TimeUnit.SECONDS);
                List<Role> roles = usermapper.queryRoleall();
                Role role = usermapper.queryName(username);
                //数据库有数据
                if (roles.contains(role)){
                    //随机设置过期时间防止缓存雪崩 缓存时间为2天 - 3天
                    redisUtil.setEx(username,role,random.nextInt(1440)+1440*2);
                }else{    //缓存 数据库都没有数据 null解决缓存穿透 设置时间为5分钟
                    redisUtil.setEx(username,role,5);
                }
                resultResponse.setCode(200);
                resultResponse.setMessage("successful");
                resultResponse.setData(role);
                return resultResponse;
                //业务逻辑over
            }catch (Exception e){
                logger.error(e.getMessage());
            }
            finally{
                //释放锁
                redissonLock.unlock();
            }
        }
            //缓存有数据
            resultResponse.setCode(200);
            resultResponse.setMessage("successful");
            resultResponse.setData(o);
            return resultResponse;
    }



    @GetMapping("/get")
    public String get() {
        return port;
    }

//    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
//    @Scheduled(cron ="0/10 * * * * ? ")
//    public void hello(){
//        System.out.println("现在时间：" + DATE_FORMAT.format(new Date()));
//        System.out.println("端口号"+port);
//    }


    @GetMapping("/test")
    public String get1() {
        System.out.println("aop hellocontrol");
        return "";
    }


}