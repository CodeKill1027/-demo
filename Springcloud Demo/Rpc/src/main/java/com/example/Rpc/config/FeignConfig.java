package com.example.Rpc.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yzj
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor  TokenBearerInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            requestTemplate.header("Authorization", request.getHeader("Authorization"));
        };
    }


    //在工厂中创建一个restTemplate对象  调用的时候具备负载均衡能力的工具
    @Bean
    @LoadBalanced   //代表ribbon负载均衡的restTemplate 客户端对象
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public IRule ribbonRule() {
        return new WeightedResponseTimeRule();//这里配置策略，和配置文件对应
    }

    @Bean
    Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}



