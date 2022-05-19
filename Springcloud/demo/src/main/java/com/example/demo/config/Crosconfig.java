//package com.example.demo.config;
//
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author yzj
// */
//@Configuration
//public class Crosconfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .allowedMethods("POST", "PUT", "GET", "OPTIONS", "DELETE")
//                .allowCredentials(true)
//                .allowedOrigins("*")
//                .maxAge(3600);
//    }
//
//
//}