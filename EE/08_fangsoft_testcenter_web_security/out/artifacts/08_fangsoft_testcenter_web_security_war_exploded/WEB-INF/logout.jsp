<%@ page import="org.fangsoft.testcenter.web.Constants" %>
<%@ page import="org.fangsoft.testcenter.model.TestResult" %>
<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/2
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="utf-8" session="false" %>

<%
    response.reset();
    HttpSession session = request.getSession(false);
    if (session != null) {
        if (session.getAttribute(
                Constants.SESSION_TESTRESULT) != null) {
            TestResult testResult = (TestResult) session.
                    getAttribute(Constants.SESSION_TESTRESULT);
            int testReservationId = (Integer) session.
                    getAttribute(Constants.SESSION_TEST_RESERVATION);
            JSPUtil.getTestCenterFacade().
                    commitTest(testResult, testReservationId);
        }
        session.invalidate();
    }
    response.sendRedirect(URLConfig.urlLoginView);
%>

