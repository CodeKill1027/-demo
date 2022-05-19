package com.example.Springcloud.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 复述,异常
 *
 * @author yzj
 * @date 2022/05/16
 */
@RestControllerAdvice
public class RedisException {
    @ExceptionHandler({RedisExceptioniml.class})
    @ResponseBody
    public HashMap<String, Object> businessExceptionHandler(HttpServletRequest request, RedisExceptioniml e) throws RedisExceptioniml {
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("code",e.getCode());
        hashMap.put("message",e.getMessage());
        return hashMap;
    }



}
