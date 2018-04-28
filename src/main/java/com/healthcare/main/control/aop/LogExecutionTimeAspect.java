package com.healthcare.main.control.aop;

import com.healthcare.main.boundry.controller.ApiController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info(String.format("%s executed in %s ms", joinPoint.getSignature(), executionTime));
        return proceed;
    }
}
