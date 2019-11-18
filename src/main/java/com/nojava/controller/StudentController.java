package com.nojava.controller;

import com.nojava.bean.Student;
import com.nojava.service.ITestService;
import com.nojava.test.datasource.DynamicSwitchDataSouce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentController {

    @Autowired
    private ITestService testService;


//    @DynamicSwitchDataSouce(dataSource = "datasource01")
    @DynamicSwitchDataSouce(dataSource = "datasource02")
    public void testService01(Student student) throws Exception {
        testService.testService01(new Student());
    }

}
