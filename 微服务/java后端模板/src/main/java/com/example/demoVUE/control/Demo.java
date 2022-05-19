package com.example.demoVUE.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yzj
 */


@RestController
//http://localhost:8087/swagger-ui/index.html
@Api(tags = "SayController|一个用来测试swagger注解的控制器")
public class Demo {

    @ApiOperation(value = "dsdsd",notes = "Dsds2323")
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String a){
        return "Hello-World"+a;
    }


    @ApiOperation(value = "dsdsd",notes = "Dsds2323")

    @ApiImplicitParam(name = "name",value = "Dsd",required = true,dataType = "String")
    @PostMapping("/hello1")
    public String hello1(@RequestParam("name") String a,@RequestParam("pwd") String b){
        return "Hello-World"+a+b;
    }






}
