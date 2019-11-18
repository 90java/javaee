package com.nojava.dao;

import com.nojava.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public interface IStudentDao {

    public void saveStudent(Student student) throws Exception;

}
