package com.example.Rpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yzj
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
public class RpcApplication {
	@Value("${server.port}")
	private String port;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
	@Scheduled(cron ="0/3 * * * * ? ")
	public void hello(){
		System.out.println("现在时间：" + DATE_FORMAT.format(new Date()));
		System.out.println("端口号"+port);
	}

	public static void main(String[] args) {
		SpringApplication.run(RpcApplication.class, args);
	}

}
