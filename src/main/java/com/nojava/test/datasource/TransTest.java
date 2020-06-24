package com.nojava.test.datasource;

import com.nojava.bean.Student;
import com.nojava.controller.StudentController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 多线程进行测试
 */
public class TransTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_dao.xml");
//        ITestService testService = (ITestService)context.getBean("testService");
//        testService.testService01(new Student());


        new Thread(()->{
            StudentController studentController = (StudentController) context.getBean("studentController");
            try {
                studentController.testService01(new Student());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{
            StudentController studentController = (StudentController) context.getBean("studentController");
            try {
                studentController.testService01(new Student());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();




    }
}
