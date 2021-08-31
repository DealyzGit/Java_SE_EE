<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/10
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<form action="addTest.do" class="modal-content animate" method="post">

    <table width="100%" border="0">

        <tr>
            <td colspan="3" bgcolor="#FFFFCC">
                主页->增加考试
            </td>
        </tr>

        <tr>
            <td width=33%>

            </td>
            <td width="31%">
                考试名称
            </td>
            <td width="36%">
                <input name="test_name" placeholder="输入考试名称" required type="text" align="center">
            </td>
        </tr>

        <tr>
            <td width=33%>

            </td>
            <td width="31%">
                考试时间
            </td>
            <td width="36%">
                <input name="time_limit" placeholder="输入考试时间" required type="text" align="center">
            </td>
        </tr>


        <tr>
            <td width=33%>

            </td>
            <td width="31%">
                考试题数
            </td>
            <td width="36%">
                <input name="question_num" placeholder="输入考试题数" required type="text" align="center">
            </td>
        </tr>


        <tr>
            <td width=33%>

            </td>
            <td width="31%">
                考试总分
            </td>
            <td width="36%">
                <input name="score" placeholder="输入考试总分" required type="text" align="center">
            </td>
        </tr>

        <tr>
            <td width=33%>

            </td>
            <td width="31%">
                考试描述
            </td>
            <td width="36%">
                <input name="description" placeholder="输入考试描述" required type="text" align="center">
            </td>
        </tr>


        <tr>
            <td width=33%>

            </td>
            <td width="31%">

            </td>

            <td width="36%">
                <button type="submit">增加新考试</button>
            </td>
        </tr>


    </table>
</form>

</body>
</html>
