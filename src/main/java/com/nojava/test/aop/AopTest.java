package com.nojava.test.aop;

public class AopTest implements IAopTest {

    @Override
    public void add() {
        System.out.println("目标类执行的方法");

        int i=1/0;

    }
}
