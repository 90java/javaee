package com.nojava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyWebAppInitializer 类中启动类 onStartup方法
 * ServletContext 中注册改servlet
 */
public class ServletTest3 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ServletTest3");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param_name=getServletContext().getInitParameter("param1");
        System.out.println("ServletTest3——param_name:"+param_name);
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("ServletTest3");
        writer.close();
    }
}
