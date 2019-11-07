package com.nojava.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *环绕通知(Around Advice)
 */
public class AroundAdvice implements MethodInterceptor {

    private MyLogger logger;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // mi.getMethod()获得将来要调用的方法的镜像
        //在目标方法执行之前做日志
        logger.log("环绕通知(Around Advice):"+methodInvocation.getMethod().getName() + " is start...");

        // 这个方法就是用来调用目标对象中的方法的
        Object returnValue = methodInvocation.proceed();

        //在目标方法执行之后做日志
        logger.log("环绕通知(Around Advice):"+methodInvocation.getMethod().getName() + " is end...");


        return returnValue;
    }


    public void setLogger(MyLogger logger) {
        this.logger= logger;
    }
}
