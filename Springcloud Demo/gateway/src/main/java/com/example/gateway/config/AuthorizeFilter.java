package com.example.gateway.config;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 授权过滤器
 *
 * @author yzj
 * @date 2022/05/07
 */
@Component
public class AuthorizeFilter  implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        System.out.println("全局BFilter前置逻辑");
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
        {
            System.out.println("全局BFilter后置逻辑");
        }));
    }
    //设置过滤器优先级，值越低优先级越高
    //也可以使用@Order注解
    @Override
    public int getOrder() {
        return  0;
    }
}