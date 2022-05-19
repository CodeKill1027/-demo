package com.example.demoVUE.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzj
 */
@RestController

public class Demo {



@GetMapping("/re")
    public String v(){

    return "dsds";
}
    @GetMapping("/re1")
    public String v2(){

        return "dsds";
    }




}
