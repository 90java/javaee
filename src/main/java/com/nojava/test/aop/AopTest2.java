package com.nojava.test.aop;

import org.springframework.stereotype.Component;

@Component("aopTest2")
public class AopTest2  {

    public void add() {
        System.out.println("目标类执行的方法");

        int i=1/0;

    }
}
