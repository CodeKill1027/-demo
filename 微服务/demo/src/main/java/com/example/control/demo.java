package com.example.control;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
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
public class demo {

    @ApiOperation(value = "dsdsd",notes = "Dsds2323")
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String a){
        return "Hello-World"+a;
    }


    @ApiOperation(value = "登录",notes = "用户登录接口")
// name：参数名
//        value：参数的汉字说明、解释
//        required：参数是否必须传
//        paramType：参数放在哪个地方
//            · header --> 请求参数的获取：@RequestHeader
//            · query --> 请求参数的获取：@RequestParam
//            · path（用于restful接口）--> 请求参数的获取：@PathVariable
//            · body（不常用）
//            · form（不常用）
//        dataType：参数类型，默认String，其它值dataType="Integer"
//        defaultValue：参数的默认值
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户账号",required = true,dataType = "String",paramType = "query",defaultValue = "null") ,
            @ApiImplicitParam(name = "pwd",value = "用户密码",required = true,dataType = "String",paramType = "query",defaultValue = "null")
    })
    @PostMapping ("/hello1")
    public String hello1(@RequestParam("name") String a,@RequestParam("pwd") String b){
        return "Hello-World"+a+b;
    }
}
