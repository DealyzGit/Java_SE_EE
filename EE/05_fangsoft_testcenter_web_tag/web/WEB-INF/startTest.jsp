<%@ taglib prefix="c" uri="https://fangsoft.com" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<%@ page import="org.fangsoft.testcenter.web.Constants,
                 org.fangsoft.testcenter.web.JSPUtil,
                 org.fangsoft.util.DataConverter,
                 org.fangsoft.testcenter.web.URLConfig"
%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="org.fangsoft.testcenter.model.*" %>
<%@ page import="java.util.List" %>
<%
    response.reset();
//    if (!JSPUtil.processNotLogin(request, response))
//        return;
    HttpSession session = request.getSession(false);
    TestResult testResult;
    int testReservationId = -1;
    if (session.getAttribute(Constants.SESSION_TESTRESULT) != null) {
        testResult = (TestResult) session.getAttribute(Constants.SESSION_TESTRESULT);

    } else {
        int testId = DataConverter.str2Int(request.getParameter("testId"));
        testReservationId = DataConverter.str2Int(request.getParameter("testReservationId"));
        testResult = JSPUtil.getTestCenterFacade().startTest(testId, testReservationId, JSPUtil.getCustomer(request));
    }
    session.setAttribute(Constants.SESSION_TESTRESULT, testResult);
    session.setAttribute(Constants.SESSION_TEST_RESERVATION, testReservationId);

    session.setMaxInactiveInterval(JSPUtil.getTestCenterFacade().getRemainingTestTime(testResult) + 30);
//    request.getRequestDispatcher("/" + URLConfig.urlStartTestView).forward(request, response);
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

<c:TestInfoTag testId='<%=testResult.getTest().getId()%>'/>
<%--<jsp:include page="/WEB-INF/testInfo.jsp"/>--%>


<table width="100%" border="0">
    <tr>
        <td colspan="3" valign="top" bgcolor="#FFFFCC">
            <strong>开始考试</strong>
        </td>
    </tr>

</table>

<%
    String urlCommit = URLConfig.urlCommitTestAction;
%>

<form action="<%=urlCommit%>" method="POST" name="takeTestForm " id="takeTestForm ">

    <table width="100% " border="0 ">
        <tr>
            <td colspan="2 " bgcolor="#FFFFCC ">
                <div align="left ">
                    <strong>考试试题</strong>
                </div>
            </td>
        </tr>
        <%
            Test test = testResult.getTest();
            if (test != null && test.getQuestion() != null) {
                List<Question> questionList = test.getQuestion();
                for (int i = 0; i < questionList.size(); ++i) {
                    Question q = questionList.get(i);
        %>

        <tr>
            <td width="6%">
                <%=i + 1%>
            </td>
            <td width="94%">
                <%=q.getName()%>
            </td>
        </tr>

        <tr>
            <td>
            </td>
            <td>
                <ol>
                    <%
                        List<ChoiceItem> items = q.getChoiceItem();
                        if (items != null) {
                            for (ChoiceItem item : items) {
                                String id = q.getId() + "_" + item.getId();
                    %>
                    <li>
                        <input type="checkbox" name="<%=id%>" id="<%=id%>">
                        <%=item.getName()%>
                    </li>

                    <%
                        }
                    %>

                </ol>
            </td>
        </tr>

        <%

                    }
                }
            }
        %>
        <tr>
            <td colspan="2">
                <div align="center">
                    <input type="submit" name="button" id="button" value="完成考试"/>
                </div>
            </td>
        </tr>

    </table>
</form>
</html>