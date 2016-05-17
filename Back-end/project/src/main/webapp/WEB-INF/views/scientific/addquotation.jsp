<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/10/2016
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Quotation</title>
</head>
<body>
<form:form action="/scientific/myactivity/addQuotation" method="post" modelAttribute="quotation">
    <div>
        <form:label path="text">Text: </form:label>
        <form:input id="text" path="text"/>
    </div>
    <div>
        <form:label path="year">Year: </form:label>
        <form:input id="year" path="year"/>
    </div>
    <div>
        <form:label path="articleName">Article Name: </form:label>
        <form:input id="articleName" path="articleName"/>
    </div>
    <div>
        <form:label path="location">Location: </form:label>
        <form:input id="location" path="location"/>
    </div>
    <div>
        <form:label path="authors">Authors</form:label>
        <form:input id="authors" path="authors"/>
    </div>
    <input type="hidden" name="userID" value=${userID}>
    <input type="hidden" name="articleID" value=${articleID}>
    <input type="submit" value="Add Quotation"/>
</form:form>
</body>
</html>
