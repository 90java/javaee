package com.nojava.dao;

import com.nojava.TestException;
import com.nojava.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository(value = "studentDao")
public class StudentDaoImpl implements IStudentDao{

    @Autowired
    @Qualifier("dynamicSourceData")
    private DataSource dataSource;

    @Override
    public void saveStudent(Student student) throws SQLException, TestException {
        //基本获取方式connection：获取的连接对象不会被springIOC管理
//        Connection connection = dataSource.getConnection();

        //通过spring的方法获取连接对象
        //与spring结合的获取方式connection:获取的连接对象会被springIOC管理
        Connection connection = DataSourceUtils.getConnection(dataSource);
        System.out.println(connection.getMetaData().getUserName());
//        connection.setAutoCommit(false);//设置手动提交
        Statement statement = connection.createStatement();
        String sql = "insert into student values(seq_student.nextval,'hhaha',sysdate,'3222')";
        statement.execute(sql);
        if(1==1){
//            throw new TestException("hahah");
//            throw new Exception("12212");
        }
//        throw new NullPointerException();

    }
}
