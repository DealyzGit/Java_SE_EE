<%@ page pageEncoding="UTF-8" %>
<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.model.Test" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%
    int testId= Integer.parseInt(request.getParameter("testId"));
    Test test =JSPUtil.getTestCenterFacade().findTestByPK(testId);
%>


<table width="69%" border="0" align="right">
    <tr>
        <td bgcolor="#FFFFCC">
            <strong>考试详细信息</strong>
        </td>
    </tr>
</table>

<table width="69%" border="0" align="right">
    <tr>
        <td width=25%>
            考试名称：
        </td>
        <td width="44%">
            <%=test.getName()%>
        </td>
    </tr>
    <tr>
        <td width=49%>
            考试时间：
        </td>
        <td width="50%">
            <%=test.getTimeLimitMin()%>
        </td>
    </tr>

    <tr>
        <td width=49%>
            考试题数：
        </td>
        <td width="50%">
            <%=test.getNumQuestion()%>
        </td>
    </tr>

    <tr>
        <td width=49%>
            考试总分：
        </td>
        <td width="50%">
            <%=test.getScore()%>
        </td>
    </tr>

    <tr>
        <td width=49%>
            考试描述：
        </td>
        <td width="50%">
            <%=test.getDescription()%>
        </td>
    </tr>

</table>
