package com.nojava.service.impl;

import com.nojava.bean.Student;
import com.nojava.dao.IStudentDao;
import com.nojava.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    IStudentDao studentDao;


    public void testService01(Student student) throws Exception {
        studentDao.saveStudent(student);
    }

    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
