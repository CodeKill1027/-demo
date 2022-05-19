package com.example.Springcloud.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源控制器
 *
 * @author yzj
 * @date 2022/05/07
 */
@RestController
@RequestMapping("/SpringSource/Resource")
public class ResourceController {

    @GetMapping("/c")
    @PreAuthorize("hasAuthority('a')")
    public String findAll2() {
        return "查询产品列表成功2！";
    }

}
