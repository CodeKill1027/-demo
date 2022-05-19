package com.example.Springcloud;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.web.client.RestTemplate;

/**
 * @author yzj
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.example.Springcloud.dao")
@EnableScheduling
public class DemoApplication {



	@Bean
	@LoadBalanced   //代表ribbon负载均衡的restTemplate 客户端对象
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); }


	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}
}
