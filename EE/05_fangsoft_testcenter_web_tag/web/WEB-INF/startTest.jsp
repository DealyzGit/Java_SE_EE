<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/2
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java"  session="false" pageEncoding="utf-8"   %>
<%@ page  import="org.fangsoft.testcenter.web.Constants,
         org.fangsoft.testcenter.model.QuestionResult,
         org.fangsoft.testcenter.model.TestResult,
         org.fangsoft.testcenter.model.Question,
         org.fangsoft.testcenter.model.ChoiceItem,
         org.fangsoft.testcenter.web.JSPUtil,
         org.fangsoft.util.DataConverter,
         org.fangsoft.testcenter.web.URLConfig"
%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    response.reset();
    if(!JSPUtil.processNotLogin(request,response))return;
    HttpSession session=request.getSession(false);
    TestResult testResult=null;
    if(session.getAttribute(Constants.SESSION_TESTRESULT)!=null){
        testResult=(TestResult)session.
                getAttribute(Constants.SESSION_TESTRESULT);
    }else{
        int testId=DataConverter.str2Int(request.getParameter("testId"));
        int testReservationId=DataConverter.str2Int(
                request.getParameter("testReservationId"));
        testResult=JSPUtil.getTestCenterFacade().startTest(testId,
                testReservationId,JSPUtil.getCustomer(request));
        session.setAttribute(Constants.SESSION_TESTRESULT, testResult);
        session.setAttribute(Constants.SESSION_TEST_RESERVATION,
                testReservationId);
    }
    session.setMaxInactiveInterval(JSPUtil.getTestCenterFacade().
            getRemainingTestTime(testResult)+30);
    request.getRequestDispatcher("/"+URLConfig.urlStartTestView).
            forward(request,response);
%>
