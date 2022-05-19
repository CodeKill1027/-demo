package com.example.openfeign.client;

import com.example.openfeign.Config.FeignConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

/**
 * @author yzj
 */
@FeignClient(name = "provder")
@Headers({"Content-Type: application/json","Accept: application/json"})
public interface ProductService {

    @GetMapping("/c")
    public String findAll2();
}
