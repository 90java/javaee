package com.nojava.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener,ServletContextAttributeListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		sce.getServletContext();
		System.out.println("appliction对象被创建");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		sce.getServletContext();
		System.out.println("appliction对象被销毁");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.print("appliction attributeAdded ");
		System.out.println(name+" : "+value);
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.print("appliction attributeRemoved ");
		System.out.println(name+" : "+value);
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.print("appliction attributeReplaced ");
		System.out.println(name+" : "+value);
		
	}

}
