package com.example.demoVUE;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yzjlyl
 */
@SpringBootApplication
@MapperScan("com.example.demoVUE.dao")
public class DemoVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoVueApplication.class, args);
	}
}
