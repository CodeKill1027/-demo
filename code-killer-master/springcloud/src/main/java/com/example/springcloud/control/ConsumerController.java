package com.example.springcloud.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class ConsumerController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/instances")
    public List<ServiceInstance>  instances(){
        List<ServiceInstance> provder = discoveryClient.getInstances("provder");
        return provder;
    }

    //服务发现
    @GetMapping("/index")
    public String index(){
     return restTemplate.getForObject("http://provder/port",String.class);
    }

    //服务发现
    @GetMapping("/index1")
    public String index1(){
        return "Dsdsd";
    }
}
