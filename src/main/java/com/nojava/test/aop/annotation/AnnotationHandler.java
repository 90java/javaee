package com.nojava.test.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * 切面类
 * */
@Component
@Aspect
public class AnnotationHandler {
	/*
	 * 在一个方法上面加上注解来定义切入点
	 * 这个切入点的名字就是这个方法的名字
	 * 这个方法本身不需要有什么作用
	 * 这个方法的意义就是:给这个@Pointcut注解一个可以书写的地方
	 * 因为注解只能写在方法、属性、类的上面,并且方法名作为切入点的名字
	 * */
	@Pointcut("execution(* com.nojava.test.aop..*.*())")
	public void myPointCut(){}
	
	
	@Before("myPointCut()")
	public void beforeTest(JoinPoint p){
		System.out.println("before----"+p.getSignature().getName()+" before...");
	}
	
	
	/*
	 * @After和@AfterReturning
	 * 
	 * @After标注的方法会在切入点上的方法结束后被调用(不管是不是正常的结束).
	 * @AfterReturning标注的方法只会在切入点上的方法正常结束后才被调用.
	 * */
	@After("myPointCut()")
	public void afterTest(JoinPoint p){
		System.out.println(p.getSignature().getName()+" after...");
	}
	
	@AfterReturning("myPointCut()")
	public void afterReturningTest(JoinPoint p){
		
		System.out.println(p.getSignature().getName()+" afterReturning");
		
	}
	
	@Around("myPointCut()")
	public Object aroundTest(ProceedingJoinPoint pjp)throws Throwable{
		System.out.println(pjp.getSignature().getName()+" is start..");
		//调用连接点的方法去执行
		Object obj = pjp.proceed();
		System.out.println(pjp.getSignature().getName()+" is end..");
		return obj;
	}
	
	
	
	//在切入点中的方法执行期间抛出异常的时候,会调用这个@AfterThrowing注解所标注的方法
	@AfterThrowing(value="myPointCut()",throwing="ex")
	public void throwingTest(JoinPoint p,Exception ex){
		System.out.println(p.getSignature().getName()+" is throwing..."+ex.getMessage());
		
	}
	
	//注:这里面的所有方法的JoinPoint类型对象都可以去掉不写
	
}
