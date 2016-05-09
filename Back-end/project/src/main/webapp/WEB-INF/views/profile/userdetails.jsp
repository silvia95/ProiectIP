<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<c:choose>
    <c:when test="${userInfo == null}">
        <h3>No user with this ID.</h3>
    </c:when>
    <c:when test="${userInfo != null}">
        <h3>Email: ${userInfo.email}</h3>
        <h3>First Name: ${userInfo.firstname}</h3>
        <h3>Last Name: ${userInfo.lastname}</h3>
        <h3>Function: ${teacherInfo.type}</h3>
        <h3>Departments:</h3>
        <ul>
            <c:forEach items="${teacherInfo.departments}" var="department">
                <li>${department}</li>
            </c:forEach>
        </ul>

        <h3>Emails:</h3>
        <ul>
            <c:forEach items="${emails}" var="email">
                <li>${email}</li>
            </c:forEach>
        </ul>
    </c:when>
</c:choose>
</body>
</html>
