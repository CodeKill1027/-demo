package com.example.Springcloud.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.example.Springcloud.entity.Sentinelpojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yzj
 * 自定义流控规则
 */
@Configuration
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
         Sentinelpojo sentinelpojo=  new Sentinelpojo(429,"未知异常");
        if (e instanceof FlowException) {
           sentinelpojo=  new Sentinelpojo(-1,"请求被限流了");
        } else if (e instanceof ParamFlowException) {
            sentinelpojo=  new Sentinelpojo(-1,"请求被热点参数限流");
        } else if (e instanceof DegradeException) {
            sentinelpojo=  new Sentinelpojo(-1,"请求被降级了");
        } else if (e instanceof AuthorityException) {
            sentinelpojo=  new Sentinelpojo(401,"没有权限访问");
        }
        String s = new ObjectMapper().writeValueAsString(sentinelpojo);
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(429);
        response.getWriter().println(s);
    }
}
