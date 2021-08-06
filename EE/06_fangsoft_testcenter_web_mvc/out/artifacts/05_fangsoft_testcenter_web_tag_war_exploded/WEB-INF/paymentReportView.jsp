<%@ page import="org.fangsoft.testcenter.model.TestReservation" %>
<%@ page import="org.fangsoft.testcenter.web.JSPUtil" %><%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/3
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {
        int testReservationId = Integer.parseInt(request.getParameter("testReservationId"));
        JSPUtil.getTestCenterFacade().getDaoFactory().getTestReservationDao().updateStatus(testReservationId, TestReservation.Status.PAYED);
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>FangSoft</title>
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
            <strong>支付结果报告</strong>
        </td>
    </tr>
    <tr>
        支付成功
    </tr>
    <tr>
        <a href="testCenterView">
            返回
        </a>
    </tr>

</table>
</body>
</html>
