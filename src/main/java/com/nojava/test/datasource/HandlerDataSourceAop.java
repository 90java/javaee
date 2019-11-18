package com.nojava.test.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面类
 */
@Aspect
@Component
public class HandlerDataSourceAop {


//    @Pointcut("@within(com.nojava.test.datasource.DynamicSwitchDataSouce)||@annotation(com.nojava.test.datasource.DynamicSwitchDataSouce)")
    @Pointcut("execution(* com.nojava.controller..*.*(..))")
    public void myPointCut(){}

    /**
     * 前置通知  在方法前调用
     * @param joinPoint
     */
    @Before("myPointCut()")
    public void doBefore(JoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        System.err.println(method.getName());
        DynamicSwitchDataSouce annotation = method.getAnnotation(DynamicSwitchDataSouce.class);//获取方法上的注解
        if(annotation == null){
            annotation = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSouce.class);//获取类上面的注解
            if(annotation == null){
                return;
            }
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotation.dataSource();
        System.err.println(dataSourceKey);
        if(!StringUtils.isEmpty(dataSourceKey)){
            //设置数据源
            HandlerDataSource.setDataSouce(dataSourceKey);
        }
        System.out.println("AOP动态切换数据源，类名"+joinPoint.getTarget().getClass().getName()
                +",方法名"+method.getName()+",数据源key"+dataSourceKey==""?"默认数据源":dataSourceKey);
    }

    /**
     *
     */
    @After("myPointCut()")
    public void after(){
        //清理当前线程设置数据源，不影响默认的数据源
        HandlerDataSource.clearDataSource();

    }


}
