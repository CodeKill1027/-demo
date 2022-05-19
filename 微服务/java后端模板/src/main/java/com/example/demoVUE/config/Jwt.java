package com.example.demoVUE.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demoVUE.config.JwtTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;

/**
 * @author yzj
 */
@Configuration
public class Jwt implements HandlerInterceptor {

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object handler) throws java.lang.Exception {
        // axios.defaults.headers.common['token'] = res.headers.authorization
        //
        //作者：林逸舟丶
        //链接：https://www.imooc.com/article/27751
        //来源：慕课网
        //本文原创发布于慕课网 ，转载请注明出处，谢谢合作
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Overwrite, Destination, Content-Type, Depth, User-Agent, Translate, Range, Content-Range, Timeout, X-File-Size, X-Requested-With, If-Modified-Since, X-File-Name, Cache-Control, Location, Lock-Token, If,token");
        HashMap<String, Object> hashMap=new HashMap<>();
        String token = request.getHeader("token");
        try {
            JwtTool.verify(token);
            return  true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            hashMap.put("msg","无效签名");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            hashMap.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            hashMap.put("msg","token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("msg","token无效");
        }
        hashMap.put("state",false);
        String s = new ObjectMapper().writeValueAsString(hashMap);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(s);
        return false;
    }
    }










