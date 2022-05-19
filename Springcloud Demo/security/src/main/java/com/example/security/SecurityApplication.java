package com.example.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableAuthorizationServer
@MapperScan("com.example.security.dao")
@EnableDiscoveryClient
@EnableScheduling
public class SecurityApplication {
	@Value("${server.port}")
	private String port;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Scheduled(fixedRate = 3000)
	public void hello(){
		System.out.println("现在时间：" + dateFormat.format(new Date()));
		System.out.println("端口号"+port);
	}

	@Bean
	public PasswordEncoder myPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
