package com.example.openfeign.controller;

import com.example.openfeign.client.ProductService;
import com.example.openfeign.client.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yzj
 */
@RestController
@RequestMapping("/user")
public class Controll {


    @Autowired
    ProductService productService;
    @GetMapping("/a")
    public String a(){
        return  "多岁的所得税";
    }


    @GetMapping("/abc")
    public String abc(){
     return   productService.findAll2();
    }

}
