
package com.example.Springcloud.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yzj
 */
@Configuration
public class Crosconfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "PUT", "GET", "OPTIONS", "DELETE")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .maxAge(3600);
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        List<String> verify = new ArrayList<>();
//        verify.add("/a");
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new Jwt());
//       // interceptorRegistration.excludePathPatterns("/**");//放行
//        interceptorRegistration.addPathPatterns(verify);  //需验证才可访问
//    }


}