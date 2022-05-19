package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author yzj
 */
@Component
public class LogFilter2 extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        System.out.println("局部过滤前");
        return (exchange,chain) -> {
            System.out.println("局部过滤后");
            return chain.filter(exchange);
        };
    }
}
