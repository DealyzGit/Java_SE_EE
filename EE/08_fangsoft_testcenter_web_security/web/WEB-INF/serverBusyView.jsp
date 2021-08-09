<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/9
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>FangSoft</title>
</head>
<body>


<c:out value="${applicationScope.app_session_guard.count}"/>

</body>
</html>
