<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/2
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="utf-8" session="false" %>
<%@ page import=" org.fangsoft.testcenter.model.Customer,
                 org.fangsoft.testcenter.web.Constants,
                 org.fangsoft.testcenter.web.JSPUtil,
                 org.fangsoft.util.DataValidator,
                 org.fangsoft.web.util.CookieUtil,
                 org.fangsoft.testcenter.web.URLConfig"
%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<>
<%
    response.reset();//清空输出提交前的所有输出内容
    String userId = DataValidator.validate(request.getParameter("userId"));
    String password = DataValidator.validate(request.getParameter("password"));
    if (request.getParameter("save") != null) {
        this.saveCookie(response, userId);
    } else {
        CookieUtil.killCookie(request, response, Constants.COOKIE_USERID);
    }
    Customer customer = JSPUtil.getTestCenterFacade().login(userId, password);
    if (customer != null) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_USERID, customer);
        request.getRequestDispatcher("/" + URLConfig.urlTestCenterView).forward(request, response);
//        return;
    } else {
        request.setAttribute(Constants.REQUEST_LOGIN_ERROR_MSG,"invalid userId or password");
        request.getRequestDispatcher("/" + URLConfig.urlLoginView).forward(request, response);
    }
%>
<%!
    private void saveCookie(HttpServletResponse response, String cookieValue) {
        Cookie cookie = new Cookie(Constants.COOKIE_USERID, cookieValue);
        cookie.setMaxAge(31536000);//1 year
        response.addCookie(cookie);
    }
%>
