package com.example.demo;

import com.example.demo.service.HzyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
@Autowired
HzyService hzyService;
    @Test
    void contextLoads() {
        System.out.println(hzyService.getHelloWorld());
    }

}
