<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/10/2016
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quotations</title>
</head>
<body>

<h3>Quotations</h3>
<table class="table table-bordered" border="1">
    <tr>
        <th>Article Name</th>
        <th>Year</th>
        <th>Text</th>
        <th>Location</th>
        <th>Authors</th>
    </tr>
    <c:forEach items="${quotationList}" var="quotation" >
        <tr>
            <td>${quotation.articleName}</td>
            <td>${quotation.year}</td>
            <td>${quotation.text}</td>
            <td>${quotation.location}</td>
            <td>${quotation.authors}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
