package com.healthcare.main.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

    @Pointcut("execution(* com.healthcare.main.boundry.controller.*.*(..))")
    public void restControllerExecution() {}
}
