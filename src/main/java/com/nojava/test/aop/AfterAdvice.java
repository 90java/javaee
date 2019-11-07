package com.nojava.test.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 返回后通知(After returning advice)
 */
public class AfterAdvice implements AfterReturningAdvice {

    //切面类
    private MyLogger logger;

    /**
     * 		//参数1 目标对象中的方法执行完返回值
     * 		//参数2 所执行方法的镜像对象
     * 		//参数3 执行方法时候所传的参数
     * 		//参数4 目标对象
     * 		//将来调用目标对象的方法之后会执行这个afterReturning方法
     * @param o
     * @param method
     * @param objects
     * @param o1
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        logger.log("返回后通知(After returning advice):"+"after returning " + " target=" + o1
                + " method Name=" + method.getName() + " args are:" + objects
                + " returnValue=" + o);
    }

    public void setLogger(MyLogger logger) {
        this.logger= logger;
    }
}
