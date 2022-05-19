package com.example.demoVUE.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author yzj
 */
@Configuration
@EnableSwagger2
public class Swagger {
@Value("${springfox.documentation.swagger-ui.enabled}")
private boolean  enable;  //开启和关闭

    @Bean
    public Docket docker(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
               .enable(enable) //开启和关闭Swwager
                .groupName("字杰")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.control")) //扫描接口
               // .paths(PathSelectors.ant("/com/example/**")) //过滤接口
                .build();
    }


    private ApiInfo apiInfo(){
        Contact contact=new Contact("喻字杰","http://182.61.53.209/","yzjlyl123@163.com");
        return new ApiInfo(
                "喻字杰的SwaggerAPI文档",
                "Bug保熟",
                "v1.0",
                "https://www.topgoer.cn/?page=2",
                   contact,
                   "apache 2.0",
                "https://httpd.apache.org/docs/2.2/developer/",
                new ArrayList()
        );
    }



}
