package com.nojava;

import com.nojava.config.SpringConfig;
import com.nojava.service.impl.TestServiceImpl;
import com.nojava.servlet.ServletTest3;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * https://www.cnblogs.com/vincentren/p/10753217.html
 * https://blog.csdn.net/zq17865815296/article/details/79464403
 *
 *
 * 这个WebApplicationInitializer实现类==web.xml
 * 所以项目启动时会先调用该实现类的onStartup方法
 * 为什么能找到这个实现类？
 * 答案
 *
 *
 * 这个实现类怎么使用呢？
 * 推荐使用idea可以查看源码并点那个现在源码，可以查到怎么使用
 */
public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("-----WebInit类启动-----");
        System.out.println("-----WebInit类启动-----");
        //相当于配置context-param
        servletContext.setInitParameter("init01","initvalue1");
        /**
         * servletContext3.0之后新增方法
         * addServlet()  addFilter() addListener()
         */
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("test3", ServletTest3.class);
        dynamic.setLoadOnStartup(3);
        dynamic.addMapping("/test3");




        //注解配置web应用上下文
//        AnnotationConfigWebApplicationContext rootContext =
//         new AnnotationConfigWebApplicationContext();
        //注册配置
//        rootContext.register(SpringConfig.class);
//        rootContext.register(SpringMvcConfig.class);
//        rootContext.scan("com.nojava");
//        rootContext.refresh();

        //前端控制器
 //       ServletRegistration.Dynamic dispatcher =
//               servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
//       dispatcher.setLoadOnStartup(1);
//       dispatcher.addMapping("/");




//        StaticClassTest.a();
//        TestServiceImpl testService = (TestServiceImpl)rootContext.getBean("testService");
//        ITestService testService = (ITestService)rootContext.getBean("testService");
        try {
//            testService.testService01(new Student());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            test();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }

    /**
     * 测试weblogic jndi数据源
     * @throws SQLException
     */
    public void test() throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_dao.xml");
        DataSource dataSource = (DataSource)context.getBean("datasource6");
        Connection connection = dataSource.getConnection();
        Connection connection1 = dataSource.getConnection();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()){
            String string = resultSet.getString(3);
            System.out.println("WebApplicationInitializer:"+string);
        }
        connection1.close();
        resultSet.close();
        connection.close();
    }
}
