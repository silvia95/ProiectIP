<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/17/2016
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Article</title>
</head>
<body>

<form:form action="/scientific/myactivity/addArticle" method="post" modelAttribute="article">
    <div>
        <form:label path="title">Title: </form:label>
        <form:input id="title" path="title"/>
    </div>
    <div>
        <form:label path="year">Year: </form:label>
        <form:input id="year" path="year"/>
    </div>
    <div>
        <form:label path="journalTitle">Journal Title: </form:label>
        <form:input id="journalTitle" path="journalTitle"/>
    </div>
    <div>
        <form:label path="authorsText">Authors</form:label>
        <form:input id="authorsText" path="authorsText"/>
    </div>
    <input type="submit" value="Add Article"/>
</form:form>

</body>
</html>
