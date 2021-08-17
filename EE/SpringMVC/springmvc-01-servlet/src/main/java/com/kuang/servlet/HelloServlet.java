package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = "";
        method = request.getParameter("method");
        if (method.equals("add")) {
            request.getSession().setAttribute("msg", "add method");
        }
        if (method.equals("delete")) {
            request.getSession().setAttribute("msg", "delete method");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
