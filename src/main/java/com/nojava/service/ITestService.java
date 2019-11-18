package com.nojava.service;

import com.nojava.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public interface ITestService {
    void testService01( Student student) throws Exception;
}
