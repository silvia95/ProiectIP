<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/17/2016
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Quotation</title>
</head>
<body>

<form:form action="/edit/quotation" method="post" modelAttribute="quotation">
<div>
    <form:label path="articleName">Article Name: </form:label>
    <form:input path="articleName" placeholder="${quotation.articleName}"/>
</div>
<div>
    <form:label path="year">Year: </form:label>
    <form:input path="year" placeholder="${quotation.year}"/>
</div>
<div>
    <form:label path="text">Text: </form:label>
    <form:input path="text" placeholder="${quotation.text}"/>
</div>
<div>
    <form:label path="location">Location: </form:label>
    <form:input path="location" placeholder="${quotation.location}"/>
</div>
<div>
    <form:label path="authors">Authors: </form:label>
    <form:input path="authors" placeholder="${quotation.authors}"/>
</div>

<input type="hidden" name="articleID"  value="${articleID}">
<input type="submit" value="Save"/>
</form:form>

</body>
</html>
