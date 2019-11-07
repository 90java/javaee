package com.nojava.test.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 前置通知（Before advice）
 */
public class BeforeAdvice implements MethodBeforeAdvice {
    //切面类
    private MyLogger logger;

    /**
     * 这个方法会在invoke(target,args); 前调用 该invoke由spring去调用
     * @param method
     * @param objects
     * @param o
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        logger.log("前置通知:"+method.getName() + " is invoked..");
    }

    public void setLogger(MyLogger logger) {
        this.logger = logger;
    }
}
