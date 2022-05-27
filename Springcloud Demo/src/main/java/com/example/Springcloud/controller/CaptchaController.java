package com.example.Springcloud.controller;

import com.example.Springcloud.util.RedisUtil;
import com.pig4cloud.captcha.GifCaptcha;
import com.pig4cloud.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class CaptchaController {
  @Autowired
  RedisUtil redisUtil;

    @RequestMapping("/SpringSource/captcha")
    public HashMap<String,Object> captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GifCaptcha captcha = new GifCaptcha(130, 48, 5);
        captcha.setFont(new Font("微软雅黑", Font.PLAIN, 28));
        String verCode = captcha.text().toUpperCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        redisUtil.setEx(key, verCode, 5);
        // 将key和base64返回给前端
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("key",key);
        hashMap.put("image",captcha.toBase64());
        return hashMap;
    }

    @GetMapping("/SpringSource/login")
    public String login( @RequestParam("very") String very,@RequestParam("key") String key ){
        // 获取redis中的验证码
        String redisCode = (String) redisUtil.get(key);
        // 判断验证码
//        if (verCode==null || !redisCode.equals(verCode.trim().toUpperCase())) {
//            return "验证码不正确";
//        }
        return "验证码正确"+very;
    }


}
