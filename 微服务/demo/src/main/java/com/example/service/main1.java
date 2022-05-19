package com.example.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yzj
 */
public class main1 {

    public static void main(String[] args)throws NoSuchFieldException,NoSuchMethodException {

        yzj annotation = (yzj) Zhuejie.class.getAnnotation(yzj.class);
        String name = annotation.name();
        System.out.println(name);

            Field name1 = Zhuejie.class.getDeclaredField("name");
        yzj1 annotation1 = name1.getAnnotation(yzj1.class);
        System.out.println(annotation1.name());

            Method test = Zhuejie.class.getDeclaredMethod("Test");
            yzj2 yzj2=test.getAnnotation(main1.yzj2.class);
        System.out.println(yzj2.name());
    }

    /**
     * @author yzj
     */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface yzj{
        String name() default "类";
    };
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface yzj1{
        String name() default "属性";
    };

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface yzj2{
        String name() default "方法";
    };


}
