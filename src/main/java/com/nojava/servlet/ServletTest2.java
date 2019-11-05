package com.nojava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "test2",urlPatterns = "/test2")
public class ServletTest2 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ServletTest2");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param_name=getServletContext().getInitParameter("param1");
        System.out.println("ServletTest2——param_name:"+param_name);

        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("ServletTest2");
        writer.close();
    }
}
