package com.example.Springcloud.aop;


import com.example.Springcloud.util.RedisUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author yzj
 */
@Aspect
@Component
public class TransactionAop {
    static Logger log = Logger.getLogger(TransactionAop.class);
    @Pointcut("execution(* com.example.Springcloud.controller.Hellocontrol.*(..))")
    public void pc1() {
    }

//    @Before(value = "pc1()")
//    public void beginTransaction(JoinPoint jp) {
//        System.out.println("before beginTransaction");
//    }

//    @After(value = "pc1()")
//    public void commit(JoinPoint jp) {
//        System.out.println("after commit");
//    }


//    @AfterReturning(value = "pc1()",returning = "returnObject")
//    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
//        System.out.println("afterReturning");
//    }
//
//    @AfterThrowing(value = "pc1()", throwing = "e")
//    public void afterThrowing(JoinPoint jp, Exception e) {
//        System.out.println("afterThrowing afterThrowing  rollback");
//        System.out.println(e.getMessage());
//    }

   @Around("pc1()")
public Object testCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("注解方式AOP拦截开始进入环绕通知.......");
       Object proceed = joinPoint.proceed();
    System.out.println("准备退出环绕......");
    return proceed;
}

}