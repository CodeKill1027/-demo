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
		System.out.println("(♥◠‿◠)ﾉﾞ  字杰微服务系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" + "`YMM'   `MM'`7MMF'   `7MF'    MMM\"\"\"AMV `7MMF'       `7MMF'`7MMF'`7MM\"\"\"YMM  \n" +
				"  VMA   ,V    MM       M      M'   AMV    MM           MM    MM    MM    `7  \n" +
				"   VMA ,V     MM       M      '   AMV     MM           MM    MM    MM   d    \n" +
				"    VMMP      MM       M         AMV      MM           MM    MM    MMmmMM    \n" +
				"     MM       MM       M        AMV   ,   MM           MM    MM    MM   Y  , \n" +
				"     MM       YM.     ,M       AMV   ,M   MM      (O)  MM    MM    MM     ,M \n" +
				"   .JMML.      `bmmmmd\"'      AMVmmmmMM .JMML.     Ymmm9   .JMML..JMMmmmmMMM \n");








	}
	}


