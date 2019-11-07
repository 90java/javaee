package com.nojava.test.aop.aopconfigXml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//这个类相当于我们之前的切面类
//只不过这个切面类中有很多方法都可以织入到切入点上面
//我们可以控制把这里的任何一个方法织入到任何一个切入点上面
/**
 * 完美切面类
 * */
public class XmlHandler {
	//这里的没个方法都是一个切面
	//切面其实就是要织入到切入点上面的代码
	
	public void beforeTest(JoinPoint p){
		System.out.println(p.getSignature().getName()+" before...");
	}
	
	
	public void afterTest(JoinPoint p){
		System.out.println(p.getSignature().getName()+" after...");
	}
	
	public void afterReturningTest(JoinPoint p){
		
		System.out.println(p.getSignature().getName()+" afterReturning");
		
	}
	
	//在和aroundAdvice结合的时候,这个方法一定要加上这个ProceedingJoinPoint类型的参数
	public Object aroundTest(ProceedingJoinPoint pjp)throws Throwable{
		//JoinPoint对象不能调用连接点所表示的方法 
		//ProceedingJoinPoint能调用连接点所表示的方法 pjp.proceed()
		//pjp.getSignature().getName()可以获得当前执行的连接点(方法)的名字
		System.out.println(pjp.getSignature().getName()+" is start..");
		//调用到连接点方法
		Object obj = pjp.proceed();
		System.out.println(pjp.getSignature().getName()+" is end..");
		return obj;
	}
	
	public void throwingTest(JoinPoint p,Exception ex){
		System.out.println(p.getSignature().getName()+" is throwing..."+ex.getMessage());
	}
}
