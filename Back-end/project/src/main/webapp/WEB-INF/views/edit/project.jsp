<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/17/2016
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Project</title>
</head>
<body>
<form:form action="/edit/project" method="post" modelAttribute="project">
<div>
    <form:label path="title">Title: </form:label>
    <form:input path="title" placeholder="${project.title}"/>
</div>
<div>
    <form:label path="director">Director: </form:label>
    <form:input path="director" placeholder="${project.director}"/>
</div>
<div>
    <form:label path="domain">Domain: </form:label>
    <form:input  path="domain" placeholder="${project.domain}"/>
</div>
    <div>
        <form:label path="startDate">Star date: </form:label>
        <form:input  path="startDate" placeholder="${project.startDate}"/>
    </div>
    <div>
        <form:label path="finishDate">Finish Date: </form:label>
        <form:input  path="finishDate" placeholder="${project.finishDate}"/>
    </div>
    <div>
        <form:label path="description">Description: </form:label>
        <form:input  path="description" placeholder="${project.description}"/>
    </div>
    <div>
        <form:label path="budget">Budget: </form:label>
        <form:input  path="budget" placeholder="${project.budget}"/>
    </div>
<input type="hidden" name="projectID"  value="${projectID}">
<input type="submit" value="Save"/>
</form:form>
</body>
</html>
