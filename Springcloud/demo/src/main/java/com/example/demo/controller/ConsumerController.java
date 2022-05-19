package com.example.demo.controller;


import com.example.demo.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * @author yzj
 */
@RestController
@RequestMapping("/provder")
public class ConsumerController {

    @GetMapping("/c")
    @PreAuthorize("hasAuthority('c')")
    public String findAll2() {
        return "查询产品列表成功2！";
    }
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    usermapper usermapper;
    @GetMapping("/instances")
    public List<ServiceInstance> instances(){
        return discoveryClient.getInstances("provder");
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




