package com.example.demoVUE.config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Calendar;

/**
 * @author yzj
 */

@Configuration
public class JwtTool {
   public static String sign="dsds4e3434242";

    public static String getToken(){
        Calendar i = Calendar.getInstance();
        i.add(Calendar.DATE,2);
        return JWT.create()
                .withClaim("userid",12)
                .withClaim("username","yzj")
                .withExpiresAt(i.getTime())
                .sign(Algorithm.HMAC256(sign));
    }

    public  static DecodedJWT verify(String token){
     return    JWT.require(Algorithm.HMAC256(sign)).build().verify(token);
    }




}

