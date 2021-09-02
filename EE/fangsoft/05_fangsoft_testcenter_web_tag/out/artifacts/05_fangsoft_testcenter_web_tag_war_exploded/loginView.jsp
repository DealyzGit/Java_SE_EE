<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ page import="org.fangsoft.web.util.CookieUtil" %>
<%@ page import="org.fangsoft.testcenter.web.Constants" %>
<%@ page import="org.fangsoft.testcenter.web.URLConfig" %>
<%@ page import="org.fangsoft.util.DataValidator" %>


<%
    String userId = CookieUtil.getCookieValue(request, Constants.COOKIE_USERID);
    String loginAction = URLConfig.urlLoginAction;
    String errorMsg = DataValidator.validate((String) request.getAttribute(Constants.REQUEST_LOGIN_ERROR_MSG));
%>

<!DOCTYPE html>
<html>
<head>
    <meta content="width=device-width, initial-scale=1" name="viewport" charset="utf-8">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        /* Set a style for all buttons */
        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        /* Extra styles for the cancel button */
        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }

        /* Center the image and position the close button */
        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
            position: relative;
        }

        img.avatar {
            width: 40%;
            border-radius: 50%;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
        }

        /* The Close Button (x) */
        .close {
            position: absolute;
            right: 25px;
            top: 0;
            color: #000;
            font-size: 35px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: red;
            cursor: pointer;
        }

        /* Add Zoom Animation */
        .animate {
            -webkit-animation: animatezoom 0.6s;
            animation: animatezoom 0.6s
        }

        @-webkit-keyframes animatezoom {
            from {
                -webkit-transform: scale(0)
            }
            to {
                -webkit-transform: scale(1)
            }
        }

        @keyframes animatezoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }

            .cancelbtn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div align="center" style="background-color: rgb(255,255,204); width: 100%; height: 5%;">
  <span style="font-size: medium; color: BLUE; ">
    <h1>
      Fangsoft考试中心
    </h1>
  </span>
</div>
<!--<h2 align="center">FangSoft考试中心</h2>-->
<form action="<%=loginAction %>" class="modal-content animate" method="get" name="loginForm">
    <%--<form action="testcenter.jsp" class="modal-content animate" method="post">--%>

    <div class="container">
        <label><b>用户名：</b></label>
        <%--        <input name="uname" placeholder="输入用户名" required type="text">--%>
        <input name="userId" type="text" id="userId" value="<%=userId %>" size="40"/>

        <label><b>密码：</b></label>
        <input name="password" placeholder="输入密码" required type="password">

        <a href="WEB-INF/login.jsp">
            <button type="submit">登录</button>
        </a>

        <label>
            <input checked="checked" name="remember" type="checkbox"> 在此计算机上记住我的信息
        </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button class="cancelbtn" type="button">取消
        </button>
        <span class="psw"><a href="loginView.jsp">忘记密码?</a></span>
    </div>
</form>

<%= errorMsg%>

</body>
</html>