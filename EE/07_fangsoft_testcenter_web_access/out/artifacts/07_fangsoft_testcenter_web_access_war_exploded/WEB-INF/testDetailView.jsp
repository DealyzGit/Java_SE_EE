<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%@ page import="org.fangsoft.testcenter.model.Test" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="https://fangsoft.com" prefix="tc"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    if (!JSPUtil.processNotLogin(request, response)) return;//未登录不能访问
    String userId = JSPUtil.getCustomer(request).getUserId();
    String urlLogout = URLConfig.urlLogout;

    int testId = Integer.parseInt(request.getParameter("testId"));
    Test test = JSPUtil.getTestCenterFacade().findTestByPK(testId);
%>

<jsp:useBean id="testbean" class="org.fangsoft.testcenter.web.bean.TestBean" scope="page">
    <jsp:setProperty name="testbean" property="testId" value='<%=Integer.parseInt(request.getParameter("testId"))%>'/>
</jsp:useBean>


<tc:notLogined />



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

<p>
    <c:out value="${sessionScope.session_userId.userId}"/>
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
                        <c:out value="${sessionScope.testbean.test.name}"/>
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


<tc:TestInfoTag testId='<%=testId%>'/>

<table width="69%" border="0" align="right">
    <tr>
        <td align="center" width=49%>
            <%
                String urlpage = "reserveTest.do?testId={testId}".replace("{testId}", String.valueOf(request.getParameter("testId")));
            %>

            <%=JSPUtil.makeLink(urlpage, "预定考试")%>
        </td>
    </tr>
</table>
</body>
</html>
