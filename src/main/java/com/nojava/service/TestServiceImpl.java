package com.nojava.service;

import com.nojava.bean.Student;
import com.nojava.dao.IStudentDao;
import com.nojava.test.datasource.DynamicSwitchDataSouce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements ITestService{

    @Autowired
    IStudentDao studentDao;


    public void testService01(Student student) throws Exception {
        studentDao.saveStudent(student);
    }

    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
