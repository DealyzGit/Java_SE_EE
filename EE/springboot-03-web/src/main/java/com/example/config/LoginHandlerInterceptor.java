package com.example.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") != null) {
            return true;
        } else {
            request.setAttribute("mg","没有权限，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;

        }
    }


}
