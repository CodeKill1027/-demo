package com.example.demo1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzj
 */
@RestController
public class ProductController {
    @GetMapping("/prodect2")
    @PreAuthorize("hasAuthority('c')")
    public String findAll2() {
        return "查询产品列表成功2！";
    }
}

