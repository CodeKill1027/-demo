package com.example.Springcloud.util;

import com.example.Springcloud.annotation.Zhujie;
import com.example.Springcloud.service.iml.ITokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 拦截器
 * @data 2020/4/21 12:22
 */
@Component
public class AutoCheckTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenDao tokenService;
    /**
     * 预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被AutoICheckToken标记的扫描
        Zhujie.AutoICheckToken methodAnnotation = method.getAnnotation(Zhujie.AutoICheckToken.class);
        if(methodAnnotation != null) {
                return tokenService.checkToken(request);
                // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        }
        //必须返回true,否则会被拦截一切请求
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{}
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{}
}


