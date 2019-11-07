package com.nojava.test.aop;

import java.lang.reflect.Method;

public class ThrowsAdvice implements org.springframework.aop.ThrowsAdvice {

    private MyLogger logger;



    //这里这个方法的名字一定要叫afterThrowing
    //参数可以是1个也可以是四个
    //1个参数的时候只能是一个异常类型的参数
    //如果是4个参数的话,参数的顺序也一定要是下面的顺序
/*    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        logger.log(e.getMessage());
    }*/

        //下面这样写也可以
		public void afterThrowing(Exception e) {

			logger.log(e.getMessage());
		}


    public void setLogger(MyLogger logger) {
        this.logger = logger;
    }


}
