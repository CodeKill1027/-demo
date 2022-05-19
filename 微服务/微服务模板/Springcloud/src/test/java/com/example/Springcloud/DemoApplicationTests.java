package com.example.Springcloud;


import com.example.Springcloud.dao.UserInfoMapper;
import com.example.Springcloud.dao.usermapper;
import com.example.Springcloud.entity.UserInfo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

  @Resource
 private usermapper usermapper;

  Logger logger = Logger.getLogger(Test.class);

@Autowired
UserInfoMapper userInfoMapper;
  @Test
  public void a(){

      UserInfo yzj = userInfoMapper.getUserInfoByUsername("yzj");
      System.out.println(yzj);

  }
  }





