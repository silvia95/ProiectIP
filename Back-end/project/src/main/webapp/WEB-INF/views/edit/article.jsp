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
    <title>Edit Article</title>
</head>
<body>

<form:form action="/edit/article" method="post" modelAttribute="article">
    <div>
        <form:label path="title">Title: </form:label>
        <form:input path="title" placeholder="${article.title}"/>
    </div>
    <div>
        <form:label path="year">Year: </form:label>
        <form:input path="year" placeholder="${article.year}"/>
    </div>
    <div>
        <form:label path="journalISSN">Journal ISSN: </form:label>
        <form:input  path="journalISSN" placeholder="${article.journalISSN}"/>
    </div>
    <input type="hidden" name="articleID"  value="${article.articleID}">
    <input type="submit" value="Save"/>
</form:form>

</body>
</html>