package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // record start time
        long start = System.currentTimeMillis();

        // Execute the connection point method, that is, the method corresponding to the location of the aspect. In this item, it means to execute registration or execute login, etc.
        Object result = pjp.proceed();

        // record end time
        long end = System.currentTimeMillis();

        // calculation time
        System.err.println("Time-consuming: " + (end - start) + "ms.");

        // Return the return value of the join point method
        return result;
    }
}