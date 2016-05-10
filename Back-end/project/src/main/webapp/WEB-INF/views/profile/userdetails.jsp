<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>

<c:choose>
    <c:when test="${teacherInfo == null}">
        <h3>No user with this ID.</h3>
    </c:when>
    <c:when test="${teacherInfo != null}">
        <h3>Email: ${teacherInfo.email}</h3>
        <h3>First Name: ${teacherInfo.firstname}</h3>
        <h3>Last Name: ${teacherInfo.lastname}</h3>
        <h3>Function: ${teacherInfo.type}</h3>
        <h3>Departments:</h3>
        <ul>
            <c:forEach items="${teacherInfo.departments}" var="department">
                <li>${department}</li>
            </c:forEach>
        </ul>

        <h3>Emails:</h3>
        <ul>
            <c:forEach items="${teacherInfo.otherEmails}" var="email">
                <li>${email}</li>
            </c:forEach>
        </ul>
    </c:when>
</c:choose>
</body>
</html>
