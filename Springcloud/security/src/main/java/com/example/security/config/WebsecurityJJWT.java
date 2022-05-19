package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
/**
 * @author yzj
 */
@Configuration
public class WebsecurityJJWT  extends AuthorizationServerConfigurerAdapter {


    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
  jwtAccessTokenConverter.setSigningKey("232oioewiodiesfief");
  //签名
        return   jwtAccessTokenConverter;
    }





    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.inMemory()
                .withClient("web")
                //id
                .secret(passwordEncoder.encode("web_password"))
                //password
                .scopes("read")
                //作用域
                .authorizedGrantTypes("authorization_code")
                //授权类型4 1code授权码授权 2静默授权 3密码authorization_code,password,refresh_token,implicit,client_credentials
                .accessTokenValiditySeconds(7200)
                .redirectUris("https://www.baidu.com")
                .and()
                .withClient("wx")
                .secret(passwordEncoder.encode("wx_password"))
                .scopes("read")
                .authorizedGrantTypes("implicit")
                .accessTokenValiditySeconds(7200)
                .redirectUris("https://www.baidu.com")
                .and()
                .withClient("qq")
                .secret(passwordEncoder.encode("qq_password"))
                .scopes("read")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(600)
                .redirectUris("https://www.baidu.com")
                .and()
                .withClient("kf")
                .secret(passwordEncoder.encode("kf_password"))
                .scopes("read")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(7200)
                .redirectUris("https://www.baidu.com");

    }

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }



}
