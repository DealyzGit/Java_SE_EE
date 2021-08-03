<%@ page import="org.fangsoft.testcenter.web.URLConfig" %><%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/3
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
int testReservationId = Integer.parseInt(request.getParameter("testReservationId"));

%>
<html>
<head>
    <meta charset="UTF-8">
    <title>FangSoft</title>
</head>
<body>

<div align="center" style="background-color: rgb(255,255,204); width: 100%; height: 10%;">
    <span style="font-size: medium; color: BLUE; ">
        <h1 >Fangsoft考试中心</h1>
    </span>
</div>

<table width="99%" border="0">
    <tr>
        <td bgcolor="#FFFFCC">
            <strong>支付</strong>
        </td>
    </tr>

    <tr>
        <td>
            <form>
                <div align="center">
                    <label >金额：</label>
                    <input type="text" name="username" value="100">
                </div>
                <div align="center">
                    <label >账号：</label>

                    <input type="text" name="userId" value="tong">
                </div>
                <div align="center">银行
                    <input type="checkbox" name="vehicle" value="Bike" checked="on">邮储银行
                    <input type="checkbox" name="vehicle" value="Bike">邮惠万家银行<br>
                </div>

                <div align="center">
                    <%
                    String url = URLConfig.urlPay.replace("{testReservationId}",String.valueOf(testReservationId));
                    %>
                    <a href="<%=url%>" class="button">支付</a>
                    <a href="testCenterView" class="button">放弃</a>
                </div>

                <style type="text/css">
                    .block {
                        width: 400px;
                        display: block;
                        margin: 5px 0;
                    }

                    .center {
                        text-align: center;
                    }

                    label {
                        display: inline-block;
                        width: 100px;
                        text-align: right;
                    }

                    input, textarea {
                        vertical-align: top;
                    }
                    .button {
                        background-color: #4CAF50; /* Green */
                        border: none;
                        color: white;
                        padding: 10px 30px;
                        text-align: center;
                        text-decoration: none;
                        display: inline-block;
                        font-size: 16px;
                    }
                </style>
            </form>
        </td>
    </tr>
</table>

</body>
</html>