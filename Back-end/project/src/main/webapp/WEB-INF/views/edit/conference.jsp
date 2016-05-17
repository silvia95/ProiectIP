<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/17/2016
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Conference</title>
</head>
<body>
<form:form action="/edit/conference" method="post" modelAttribute="conference">
    <div>
        <form:label path="name">Name: </form:label>
        <form:input path="name" placeholder="${conference.name}"/>
    </div>
    <div>
        <form:label path="location">Location: </form:label>
        <form:input path="location" placeholder="${conference.location}"/>
    </div>
    <div>
        <form:label path="year">Year: </form:label>
        <form:input path="year" placeholder="${conference.year}"/>
    </div>
    <div>
        <form:label path="details">Details: </form:label>
        <form:input path="details" placeholder="${conference.details}"/>
    </div>
    <input type="hidden" name="conferenceID"  value="${conference.conferenceID}">
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
