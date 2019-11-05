package com.nojava.service;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestService {

    public void testService01(){
        System.out.println("testService01 测试");
    }

}
