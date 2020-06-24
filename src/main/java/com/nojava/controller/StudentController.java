package com.nojava.controller;

import com.nojava.bean.Student;
import com.nojava.service.ITestService;
import com.nojava.test.datasource.DynamicSwitchDataSouce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    @Autowired
    private ITestService testService;


//    @DynamicSwitchDataSouce(dataSource = "datasource01")
    @DynamicSwitchDataSouce(dataSource = "datasource02")
    @RequestMapping("/testService01")
    @ResponseBody
    public String testService01(Student student) throws Exception {
        testService.testService01(new Student());
        return "testService01";
    }

}
