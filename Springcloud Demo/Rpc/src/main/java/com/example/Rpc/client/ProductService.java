package com.example.Rpc.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yzj
 */
@FeignClient(name = "SpringSource")
@Headers({"Content-Type: application/json","Accept: application/json"})
public interface ProductService {

    @GetMapping("/SpringSource/get")
    public String get();
}
