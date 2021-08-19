<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/19
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>

  <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
      <input type="file" name="file">
      <input type="submit" value="upload">
      <a href="/download">下载图片</a>
  </form>


  </body>
</html>
