<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="https://fangsoft.com" prefix="tc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="org.fangsoft.testcenter.model.TestResult" %>
<%@ page import="org.fangsoft.testcenter.web.Constants" %>
<%@ page import="org.fangsoft.util.DataConverter" %>
<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.model.Test" %>

<%
    session = request.getSession(false);
    TestResult testResult;
    int testReservationId = -1;
    int testId = -1;
//    if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
//        testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);
//
//    } else {
    testId = DataConverter.str2Int(request.getParameter("testId"));
    testResult = JSPUtil.getTestCenterFacade().startTest(testId, testReservationId, JSPUtil.getCustomer(request));
//    }
    testReservationId = DataConverter.str2Int(request.getParameter("testReservationId"));

    session.setAttribute("session_testresult", testResult);
    session.setAttribute("testReservationId", testReservationId);

    Test test = JSPUtil.getTestCenterFacade().findTestByPK(testId);
    session.setMaxInactiveInterval(JSPUtil.getTestCenterFacade().getRemainingTestTime(testResult) + 300);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>FangSoft</title>
</head>

<div align="center" style="background-color: rgb(255,255,204); width: 100%; height: 15%;">
    <span style="font-size: medium; color: BLUE; ">
        <h1>Fangsoft考试中心</h1>
    </span>
</div>

<table width="100%" border="0">
    <tr>
        <td colspan="3" valign="top" bgcolor="#FFFFCC">
            <strong>考试详细信息</strong>
        </td>
    </tr>
</table>


<table width="100%" border="0" align="center">
    <tr>
        <td colspan="2" bgcolor="#FFFFCC">
            <div align="left">
                <strong>开始考试</strong>
            </div>
        </td>
    </tr>
</table>

<tc:TestInfoTag test="<%=test%>"/>

<table width="100%" border="0" align="center">
    <tr>
        <td>
            <div align="left">
                参考人：
            </div>
        </td>
        <td>
            ${sessionScope.session_userId.userId }
        </td>
    </tr>
</table>


<form action="commitTest" method="get" name="takeTestForm">
    <table width="100%" border="0">

        <tr>
            <td colspan="2" bgcolor="#FFFFCC">
                <div align="left">
                    <strong>考试试题</strong>
                </div>
            </td>
        </tr>

        <c:if test="${! empty sessionScope.session_testresult.test && ! empty sessionScope.session_testresult.test.question}">
            <c:set var="questionNum" value="0" scope="page"></c:set>
            <c:forEach var="question" begin="0" items="${sessionScope.session_testresult.test.question}">
                <c:set var="questionNum" value="${pageScope.questionNum+1}" scope="page"></c:set>

                <tr>
                    <td width="6%">
                            ${pageScope.questionNum }
                    </td>
                    <td width="94%">
                            ${question.name}
                    </td>
                </tr>

                <tr>
                    <td>
                    </td>

                    <td>
                        <ol>
                            <c:if test="${!empty question.choiceItem}">
                                <c:forEach var="item" items="${question.choiceItem}">
                                    <c:set var="id" value="${question.id}_${item.id}" scope="page"></c:set>
                                    <li>
                                        <input type="checkbox" name="${pageScope.id}" id="${pageScope.id}"/>
                                        <c:out value="${item.name}"/>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ol>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <tr>
            <td colspan="2">
                <div align="center">
                    <input type="submit" name="button" id="button"
                           value="完成考试"/>
                </div>
            </td>
        </tr>
    </table>
</form>

</html>