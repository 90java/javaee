package com.nojava;

import com.nojava.config.SpringConfig;
import com.nojava.service.TestService;
import com.nojava.servlet.ServletTest3;
import com.nojava.servlet.StaticClassTest;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Arrays;

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
        System.out.println("WebInit类启动");

        //相当于配置context-param
        servletContext.setInitParameter("init01","initvalue1");

        //注解配置web应用上下文
        AnnotationConfigWebApplicationContext rootContext =
         new AnnotationConfigWebApplicationContext();
        //注册配置
        rootContext.register(SpringConfig.class);
//        rootContext.register(SpringMvcConfig.class);
//        rootContext.scan("com.nojava");
        rootContext.refresh();

        //servletContext3.0之后新增方法
        //addServlet()  addFilter() addListener()
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("test3", ServletTest3.class);
        dynamic.setLoadOnStartup(3);
        dynamic.addMapping("/test3");


//        StaticClassTest.a();
        TestService testService = (TestService)rootContext.getBean("testService");
        testService.testService01();
        
    }
}
