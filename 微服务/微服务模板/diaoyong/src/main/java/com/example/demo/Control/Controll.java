package com.example.demo.Control;

import com.example.demo.client.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yzj
 */
@RestController
public class Controll {


    @Autowired
    ProductService productService;
    @GetMapping("/abc")
    public HashMap<String, Object> abc(){
        return  productService.index1();
    }

}
