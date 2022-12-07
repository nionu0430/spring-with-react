package com.coocon.portal.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAop {
    @Pointcut("execution(* com.coocon.portal.demo.user.*.*(..))")
    private void loggin() {}

    @Pointcut("execution(* com.coocon.portal.demo.user.UserController.*(*))")
    private void UserController() {}

    @Around("UserController()"  )
    public Object logger(ProceedingJoinPoint pjp){
        Object result = null;
        try {
            MDC.get("traceId");

            long before = System.currentTimeMillis();
            log.debug("Controller Start");
            result = pjp.proceed();
            long after = System.currentTimeMillis();
            log.debug("Controller finish in {}ms" , after-before);

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
