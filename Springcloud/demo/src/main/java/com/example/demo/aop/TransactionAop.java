package com.example.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author yzj
 */
@Aspect
@Component
public class TransactionAop {
    @Pointcut("execution(* com.example.demo.service.impl.runable.*(..))")
    public void pc1(){ }

    /**
     * 回国后
     * 提交
     * 开始事务
     *
     * @param joinPoint    连接点
     * @param returnObject 返回对象
     */
    @Before(value = "pc1()")
    public void beginTransaction(JoinPoint jp) {
        System.out.println("before beginTransaction");
    }

//    @After(value = "pc1()")
//    public void commit(JoinPoint jp) {
//        System.out.println("after commit");
//    }
//


//    @AfterReturning(value = "pc1()",returning = "returnObject")
//    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
//        System.out.println(returnObject);
//        System.out.println(joinPoint.getSignature().getName());
//        System.out.println("afterReturning");
//    }
//
//    @AfterThrowing(value = "pc1()", throwing = "e")
//    public void afterThrowing(JoinPoint jp, Exception e) {
//        System.out.println("afterThrowing afterThrowing  rollback");
//        System.out.println(e.getMessage());
//    }
//
//    @Around(value = "pc1()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            System.out.println("around");
//            System.out.println(joinPoint.getSignature().getName());
//            return joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//
//        }
//    }


}
