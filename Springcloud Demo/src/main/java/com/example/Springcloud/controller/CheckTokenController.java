package com.example.Springcloud.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.example.Springcloud.annotation.Zhujie;
import com.example.Springcloud.service.iml.ITokenDao;
import com.example.Springcloud.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Cc
 * @data 2020/4/21 14:44
 */
@RestController
@RequestMapping("/SpringSource")
public class CheckTokenController {

    @Autowired
    private ITokenDao tokenService;

    /**
     * @Description 创建一个Tocken 并放到redis中
     * @param:
     * @return java.lang.String
     * @author Cc
     * @date 2020/4/21 15:56
     */
    @GetMapping("/getToken")
    public ResultResponse getToken() {
        ResultResponse resultResponse = new ResultResponse();
        String token = tokenService.createToken();
        if (StringUtils.isNotEmpty(token)) {
               resultResponse.setCode(200);
               resultResponse.setMessage("successful");
               resultResponse.setData(token);
               return resultResponse;
        }
        resultResponse.setCode(-1);
        resultResponse.setMessage("fail");
        resultResponse.setData("获取Token失败");
        return resultResponse;
    }

    /**
     * @Description 访问该接口，如果Head 中不带 token测试是否会进入该方法
     * @param:
     * @return java.lang.String
     * @author Cc
     * @date 2020/4/21 15:56
     */
    @Zhujie.AutoICheckToken
    @GetMapping("/testToken")
    public ResultResponse testIdempotence() {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(200);
        resultResponse.setMessage("successful");
        resultResponse.setData("test");
        return resultResponse;
    }


}


