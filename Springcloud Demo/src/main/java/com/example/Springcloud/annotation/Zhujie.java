package com.example.Springcloud.annotation;
import java.lang.annotation.*;


/**
 * zhujie
 *
 * @author yzj
 * @date 2022/05/05
 */

public class Zhujie {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
 public   @interface MyAnno {
        String value() default " " ;
    }

    @Target(ElementType.METHOD)               //表示它只能放在方法上
    @Retention(RetentionPolicy.RUNTIME)    //retentionPolicy.RUNTIME表示它在运行时
    public @interface AutoICheckToken {
    }


    }
