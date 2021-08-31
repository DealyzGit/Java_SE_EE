<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.fangsoft.testcenter.model.TestReservation" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.fangsoft.testcenter.model.Customer" %>
<%@ page import="org.fangsoft.testcenter.web.Constants" %>
<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%@ page import="javafx.beans.binding.ObjectExpression" %>
<%@ page import="org.fangsoft.testcenter.web.bean.TestReservationListBean" %><%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/2
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    int testId = 0;
    try {
        testId = Integer.parseInt(request.getParameter("testId"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (testId > 0) {
        Customer customer = (Customer) session.getAttribute(Constants.SESSION_USERID);
        JSPUtil.getTestCenterFacade().reserveTest(testId, customer);
    }

%>


<%
    request.getRequestDispatcher("/" + URLConfig.urlTestCenterView).forward(request, response);
%>