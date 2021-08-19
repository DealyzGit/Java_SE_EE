<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/19
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<%--    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.6.0.js"></script>--%>
    <script>
        function a(){
            $.post({
                url:"${pageContext.request.contextPath}/a1",
                data:{"name":$("#username").val()},
                success:function (data){
                    alert(data);
                }
                })
        }
</script>
</head>
<body>

用户名：
<input type="text" id="username" onblur="a()"/>

</body>
</html>
