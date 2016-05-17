<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cristian
  Date: 17.05.2016
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search results:</title>
</head>
<body>
<h3>Results</h3>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Journal ISSN</th>
    </tr>
    <c:forEach items="${results}" var="article" >
    <tr>
        <td>${article.title}</td>
        <td>${article.year}</td>
        <td><a href="/scientific/journaldetails?journalISSN=${article.journalISSN}"> ${article.journalISSN} </a></td>
    </tr>
    </c:forEach>
</body>
</body>
</html>
