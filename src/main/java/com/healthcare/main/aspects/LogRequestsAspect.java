package com.healthcare.main.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogRequestsAspect {

    private Logger LOGGER = LoggerFactory.getLogger(LogRequestsAspect.class);

    @Before(value = "com.healthcare.main.aspects.CommonJoinPointConfig.restControllerExecution()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("Request: {}", joinPoint);
    }

    @After(value = "com.healthcare.main.aspects.CommonJoinPointConfig.restControllerExecution()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("Request has been executed: {}", joinPoint);
    }
}
