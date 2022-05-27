package com.example.Springcloud.util;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 汽车icheck牌例外
 * 自定义异常统一处理
 *
 * @author yzj
 * @date 2022/05/06
 */
@RestControllerAdvice
public class AutoICheckTokenException {


    @ExceptionHandler({BaseBusinessException.class})
    public HashMap<String, Object> businessExceptionHandler( HttpServletRequest request ,BaseBusinessException e) {
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("code",e.getCode());
        hashMap.put("message",e.getMessage());
        return hashMap;
    }
}
