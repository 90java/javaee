package com.nojava.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 注解 注册监听器
 */
@WebListener("aaa")
public class AnnotationApplicationListener implements ServletContextListener,ServletContextAttributeListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		sce.getServletContext();
		System.out.println("注解  appliction对象被创建a");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		sce.getServletContext();
		System.out.println("注解  appliction对象被销毁a");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.print("注解  appliction attributeAdded ");
		System.out.println(name+" : "+value);
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.print("注解  appliction attributeRemoved ");
		System.out.println(name+" : "+value);
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.print("注解  appliction attributeReplaced ");
		System.out.println(name+" : "+value);
		
	}

}
