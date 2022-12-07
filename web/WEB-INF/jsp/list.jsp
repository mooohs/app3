<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/customer/"/>
    <meta charset="utf-8">
    <title>顾客列表</title>
</head>
<body>
<h1>顾客列表</h1>
<table>
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>密码</th>
        <th>vip</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${customerList}" var="customer" varStatus="s">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.password}</td>
            <td>${customer.vip}</td>
            <td><a href="mod?id=${customer.id}">修改</a>
                <a href="del?id=${customer.id}">删除</a></td>

        </tr>
    </c:forEach>
</table>
<c:if test="${page>1}">
    <a href="list?page=${page-1}">上一页</a>
</c:if>
<c:if test="${page<pageCount}">
    <a href="list?page=${page+1}">下一页</a>
</c:if>
</body>
</html>
