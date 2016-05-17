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
    <title>Add Project</title>
</head>
<body>

<form:form action="/scientific/myactivity/addProject" method="post" modelAttribute="project">
    <div>
        <form:label path="title">Title: </form:label>
        <form:input id="title" path="title"/>
    </div>
    <div>
        <form:label path="domain">Domain: </form:label>
        <form:input id="domain" path="domain"/>
    </div>
    <div>
        <form:label path="director">Direct: </form:label>
        <form:input id="director" path="director"/>
    </div>
    <div>
        <form:label path="startDate">Start Date: </form:label>
        <form:input id="startDate" path="startDate"/>
    </div>
    <div>
        <form:label path="finishDate">Finish Date: </form:label>
        <form:input id="finishDate" path="finishDate"/>
    </div>
    <div>
        <form:label path="description">Description: </form:label>
        <form:input id="description" path="description"/>
    </div>
    <div>
        <form:label path="budget">Budget: </form:label>
        <form:input id="budget" path="budget"/>
    </div>


    <input type="submit" value="Add Project"/>
</form:form>

</body>
</html>
