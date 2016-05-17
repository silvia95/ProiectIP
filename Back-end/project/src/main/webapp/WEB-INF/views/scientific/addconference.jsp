<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/17/2016
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Conference</title>
</head>
<body>

<form:form action="/scientific/myactivity/addConference" method="post" modelAttribute="conference">
    <div>
        <form:label path="name">Name: </form:label>
        <form:input id="name" path="name"/>
    </div>
    <div>
        <form:label path="location">Location: </form:label>
        <form:input id="year" path="location"/>
    </div>
    <div>
        <form:label path="year">Year: </form:label>
        <form:input id="year" path="year"/>
    </div>
    <div>
        <form:label path="details">Details: </form:label>
        <form:input id="details" path="details"/>
    </div>
    <input type="hidden" name="conferenceID" value=${conference.conferenceID}>
    <input type="submit" value="Add Quotation"/>
</form:form>

</body>
</html>
