package com.nojava.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletTest1 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ServletTest1初始化");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramValue=getServletContext().getInitParameter("contextConfigLocation");
        System.out.println(paramValue);
    }
}
