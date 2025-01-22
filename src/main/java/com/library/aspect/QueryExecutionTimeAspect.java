package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QueryExecutionTimeAspect {

    @Before("execution(* com.library.repository.*.*(..))")
    public void logQueryExecutionTime(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        
        // Log method entry
        System.out.println("Executing query method: " + joinPoint.getSignature().getName());
        
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time for method " + joinPoint.getSignature().getName() + ": " + (endTime - startTime) + "ms");
    }
}
