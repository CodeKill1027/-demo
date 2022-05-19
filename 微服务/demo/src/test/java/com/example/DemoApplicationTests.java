package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class DemoApplicationTests {

    public static void main(String[] args) {
        DemoApplicationTests demoApplicationTests=new DemoApplicationTests();
        demoApplicationTests.Test();
        Class  ClassaClass = demoApplicationTests.getClass();
        System.out.println(ClassaClass.hashCode());
    }
    @yzj()
    public void Test(){
        System.out.println("Dsds");
    }
    @Target({ElementType.METHOD,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface yzj{
        String name() default "yzj";
    }



    @Test
    public void b(){
        Set<String> set=new HashSet<>();

        set.add("ddsds");
        set.add("dsds");

        for (String a:set) {
            System.out.println(a);
        }


    }
}


