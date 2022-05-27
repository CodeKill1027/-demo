package com.example.demo.config;

import com.example.demo.service.HzyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yzj
 */
@Configuration
@Import(HzyProperties.class)
public class AutoConfig {
    @Bean
    public HzyService hzyService() {
        return new HzyService();
    }

}

