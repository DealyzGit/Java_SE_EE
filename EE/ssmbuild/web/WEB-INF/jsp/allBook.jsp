<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/18
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="container" align="center">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>
                        书籍列表—————显示书籍
                    </small>
                </h1>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 column">
                <a href="${pageContext.request.contextPath}/book/toAddBook" class="btn btn-primary">新增书籍</a>
            </div>
            <div class="col-md-4 column">
                <a href="${pageContext.request.contextPath}/book/allBook" class="btn btn-primary">显示全部书籍</a>
            </div>

            <div class="col-md-4 column">
                <form action="${pageContext.request.contextPath}/book/queryBook" method="post" style="float: right">
                    <span style="color: red;font-weight: bold">${error}</span>
                    <input type="text" class="form-inline" name="bookName" placeholder="请输入要查询的书籍名称" >
                    <input type="submit" value="查询书籍" class="btn btn-primary">
                </form>
            </div>

        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>书籍编号</th>
                    <th>书籍名称</th>
                    <th>书籍数量</th>
                    <th>书籍详情</th>
                    <th>操作</th>
                </tr>
                </thead>
                <%-- 遍历               --%>
                <tbody>
                <c:forEach var="book" items="${list}">
                    <tr>
                        <td>${book.bookID}</td>
                        <td>${book.bookName}</td>
                        <td>${book.bookCounts}</td>
                        <td>${book.detail}</td>
                        <td>

                            <a href="${pageContext.request.contextPath}/book/toUpdate?id=${book.bookID}">修改</a>  &nbsp;| &nbsp;
                            <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

</div>
</body>
</html>
