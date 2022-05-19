package com.example.security;

import com.example.security.dao.usermapper;
import com.example.security.entity.SysUser;
import com.example.security.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	usermapper usermapper;

	@Test
	void contextLoads() {
		System.out.println(usermapper.accordingToNameQueryRole("yzj"));
	}



}
