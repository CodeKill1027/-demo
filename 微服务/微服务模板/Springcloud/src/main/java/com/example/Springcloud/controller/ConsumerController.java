package com.example.Springcloud.controller;



import com.example.Springcloud.dao.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author yzj
 */
@RestController
public class ConsumerController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    usermapper usermapper;



    @GetMapping("/instances")
    public List<ServiceInstance> instances(){
        return discoveryClient.getInstances("provder");
    }

    //服务发现
    @GetMapping("/index")
    public Object index() {
       return  this.restTemplate.getForObject("http://provder/port", Object.class) ;
    }

        //服务发现
        @GetMapping(value = "/port")
        public HashMap<String, Object> index1() {
            HashMap<String, Object> hashMap =new HashMap<>();
            hashMap.put("a",a);

            return hashMap;
     }
    Logger logger = Logger.getLogger(ConsumerController.class);
     @PostMapping("/a")
    public Object a(){
        return  usermapper.SelectAll();
     }


     @Value("${server.port}")
     private  String a;
    @PostMapping("/hello1")
    public String hello1(){
        logger.info("info");
        logger.error("错误");
        logger.debug("dbug");
        return a;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 3000)
    public void hello(){
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }



}




