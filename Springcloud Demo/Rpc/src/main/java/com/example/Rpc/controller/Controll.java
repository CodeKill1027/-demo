package com.example.Rpc.controller;


import com.example.Rpc.client.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制
 *
 * @author yzj
 * @date 2022/05/15
 */
@RestController
public class Controll {
    @Autowired
    ProductService productService;
    @GetMapping("/a")
    public String a(){
        return  "多岁的所得税";
    }


    @GetMapping("/abc")
    public String abc(){
     return   productService.get();
    }

}
