<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/9/2016
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Profile</title>
</head>
<body>

<form:form action="/profile/edit" method="post" modelAttribute="profileForm">
    <div>
        <form:label path="email">E-mail: </form:label>
        <form:input id="email" path="email" placeholder="${user.email}" disabled="true"/>
    </div>
    <div>
        <form:label path="firstname">First Name: </form:label>
        <form:input id="firstname" path="firstname" placeholder="${user.firstname}"/>
    </div>
    <div>
        <form:label path="lastname">Last Name: </form:label>
        <form:input id="lastname" path="lastname" placeholder="${user.lastname}"/>
    </div>
    <div>
        <form:label path="password">Password: </form:label>
        <form:input id="password" path="password"/>
        <form:label path="confirmedPassword">Confirm Password: </form:label>
        <form:input id="confirmedPassword" path="confirmedPassword"/>
    </div>
        <input type="submit" value="Save"/>
</form:form>

<c:if test="${message != null}">
    ${message}
</c:if>

</body>
</html>