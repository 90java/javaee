package com.nojava.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * https://www.cnblogs.com/vincentren/p/10753217.html
 * https://blog.csdn.net/zq17865815296/article/details/79464403
 *
 *
 * 这个WebApplicationInitializer实现类==web.xml
 * 所以项目启动时会先调用该实现类的onStartup方法
 * 为什么能找到这个实现类？
 *
 * 这个实现类怎么使用呢？
 * 推荐使用idea可以查看源码并点那个现在源码，可以查到怎么使用
 */
public class WebInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("WebInit类启动");
        AnnotationConfigWebApplicationContext rootContext =
         new AnnotationConfigWebApplicationContext();
       rootContext.register(SpringConfig.class);
    }
}
