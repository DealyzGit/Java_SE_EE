<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%@ page import="org.fangsoft.testcenter.model.Test" %><%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/2
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        FangSoft
    </title>
</head>
<body>
<div align="center" style="background-color: rgb(255,255,204); width: 100%; height: 10%;">
  <span style="font-size: medium; color: BLUE; ">
    <h1>
      Fangsoft考试中心
    </h1>
  </span>
</div>

<%
    int testId = 0;
    if (!JSPUtil.processNotLogin(request, response)) return;//未登录不能访问
    String userId = JSPUtil.getCustomer(request).getUserId();
    String urlLogout = URLConfig.urlLogout;
    Test test =JSPUtil.getTestCenterFacade().findTestByPK(testId);
%>
<p>
    <%=userId%>
    <%=JSPUtil.makeLink(urlLogout, "登出") %>

</p>

<table width="30%" border="0" align="left">
    <tr>
        <td bgcolor="#FFFFCC">
            <strong>考试</strong>
        </td>
    </tr>
    <tr>
        <td>
            <div align="left">
                <ul>
                    <li>
                            java
                    </li>
                </ul>
            </div>
        </td>
    </tr>
</table>

<table width="69%" border="0" align="right">
    <tr>
        <td bgcolor="#FFFFCC">
            <strong>考试详细信息</strong>
        </td>
    </tr>
</table>

<jsp:include page="testInfo.jspf"/>

</body>
</html>
