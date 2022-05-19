package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

/**
 * @author yzj
 */
@FeignClient(value = "provder")
public interface ProductService {
    @GetMapping("/port")
    public HashMap<String, Object> index1();

}
