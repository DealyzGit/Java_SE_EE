<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%@ page import="org.fangsoft.testcenter.model.Test" %>
<%@ page import="org.fangsoft.testcenter.web.tag.TestInfoTag" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="myjstl" %>

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

//    if (!JSPUtil.processNotLogin(request, response)) return;//未登录不能访问
    String userId = JSPUtil.getCustomer(request).getUserId();
    String urlLogout = URLConfig.urlLogout;
    int testId = Integer.parseInt(request.getParameter("testId"));
    Test test = JSPUtil.getTestCenterFacade().findTestByPK(testId);

%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="https://fangsoft.com" prefix="tc"%>
<tc:notLogined/>


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
                        <%=test.getName()%>
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


<tc:TestInfoTag test='${test}'/>
<%--<jsp:include page="/WEB-INF/testInfo.jsp"/>--%>

<table width="69%" border="0" align="right">
    <tr>
        <td align="center" width=49%>
            <%
                String urlpage = "testCenterView?testId={testId}".replace("{testId}", String.valueOf(test.getId()));
            %>
            <%=JSPUtil.makeLink(urlpage, "预定考试")%>
            </a>
        </td>
    </tr>
</table>
</body>
</html>
