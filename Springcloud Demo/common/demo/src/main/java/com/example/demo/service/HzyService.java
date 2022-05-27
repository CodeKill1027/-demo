package com.example.demo.service;

import com.example.demo.config.HzyProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yzj
 */
public class HzyService {
    @Autowired
    HzyProperties hzyProperties;
    public String getHelloWorld() {
        return "hello world"+hzyProperties.getName();
    }
}

