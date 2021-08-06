<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.model.TestResult" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>

<%@ taglib uri="https://fangsoft.com" prefix="tc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
    TestResult testResult = null;
    int testResultId= Integer.parseInt(request.getParameter("testResultId"));
    testResult=JSPUtil.getTestCenterFacade().getDaoFactory().getTestResultDao().findTestResultByPK(testResultId);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div align="center" style="background-color: rgb(255,255,204); width: 100%; height: 10%;">
    <span style="font-size: medium; color: BLUE; ">
        <h1>Fangsoft考试中心</h1>
    </span>
</div>

<table width="99%" border="0">
    <tr>
        <td bgcolor="#FFFFCC">
            <strong>考试结果报告</strong>
        </td>
    </tr>
</table>

<table width="99%" border="0">
    <tr>
        <td width=50%>
            参考人
        </td>

        <td width=49%>
           <c:out value="${sessionScope.session_userId.userId}"/>
        </td>
    </tr>
    <tr>
        <td width=50%>
            是否通过考试
        </td>

        <td width=49%>
            <%=testResult.getResult()%>
        </td>
    </tr>

    <tr>
        <td width=50%>
            考试得分
        </td>

        <td width=49%>
            <%=testResult.getScore()%>
        </td>
    </tr>
    <tr>
        <td width=50%>
            考试开始时间
        </td>

        <td width=49%>
            <%=testResult.getStartTime()%>
        </td>
    </tr>
    <tr>
        <td width=50%>
            考试结束时间
        </td>
        <td width=49%>
            <%=testResult.getEndTime()%>
        </td>
    </tr>

</table>


<table width="99%" border="0">
    <tr>
        <td bgcolor="#FFFFCC">
            <strong>详细考试结果</strong>
        </td>
    </tr>
</table>
<table width="99%" border="0">
    <tr>
        <td width=25%>
            题号
        </td>
        <td width=25%>
            您的答案
        </td>

        <td width=25%>
            参考正确答案
        </td>

        <td width=24%>
            对错
        </td>
    </tr>
</table>


<table width="99%" border="0">
    <% for (int i = 0; i < testResult.getTest().getNumQuestion(); i++) { %>

    <tr>
        <td width=25%>
            <%=i%>
        </td>
        <td width=25%>
            <%=testResult.getQuestionResult(i).getAnswer()%>
        </td>

        <td width=25%>
            <%=testResult.getTest().getQuestion().get(i).getAnswer()%>
        </td>

        <td width=24%>
                <%=testResult.getQuestionResult(i).isResult()?"right":"wrong"%>
    </tr>

    <%
        }
    %>
</table>


<div align="center">
    <a href="<%=URLConfig.urlTestCenterView%>">
        返回
    </a>
</div>

</body>
</html>
