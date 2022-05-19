package com.example.security.config;

import com.example.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration

public class WebSecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl UserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //放行
//                .antMatchers("/profile/**").permitAll()
//                .antMatchers("/common/download**").permitAll()
//                .antMatchers("/common/download/resource**").permitAll()
                //所有资源必须授权后访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //关闭跨站请求防护
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //UserDetailsService类
//        auth.inMemoryAuthentication().withUser("yzj")
//                .password(passwordEncoder.encode("123456"))
//                .authorities("sys:admin");

        auth.userDetailsService(UserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }





}
