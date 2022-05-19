package com.example.Springcloud;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springcloud应用程序
 *
 * @author yzj
 * @date 2022/05/01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("com.example.Springcloud.dao")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableTransactionManagement
public class SpringcloudApplication {
	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudApplication.class, args);
	}




}
