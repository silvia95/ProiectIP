<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/21/2016
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Project</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/container.css"/>"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>


</head>
<body>

<div class="loader"
     style=" display:none; position: fixed; width: 100%; height: 100%; background-color: rgba(0,0,0,0.7); color: #fff;  z-index: 9999999;">
    <img src="load.gif" style="position: absolute; top:20%; left:40%;">
</div>
<section id="main_section">
    <section id="header_section">
        <a href="/"><img id="logo" src="/resources/img/logo-fii.png"/></a>
        <a href="/profile/userdetails?userID=${sessionScope.get("userID")}" class="btn btn-default">My Profile</a>
        <a href="/profile/edit" class="btn btn-default">Edit Profile</a>
        <a href="/scientific/myactivity" class="btn btn-default">My Activity</a>
        <a href="/report" class="btn btn-default">Generate Report</a>

        <a href="/logout" class="btn btn-default logout-btn">Logout</a>
        <a href="/search/" class="btn btn-default search-btn">Search</a>
    </section>

    <section id="da_big_section">
<form:form action="/edit/project" method="post" modelAttribute="project">
<div class="form-group">
    <form:label path="title">Title: </form:label>
    <form:input cssClass="form-control" path="title" placeholder="${project.title}"/>
</div>
<div class="form-group">
    <form:label path="director">Director: </form:label>
    <form:input cssClass="form-control" path="director" placeholder="${project.director}"/>
</div>
<div class="form-group">
    <form:label path="domain">Domain: </form:label>
    <form:input cssClass="form-control"  path="domain" placeholder="${project.domain}"/>
</div>
    <div class="form-group">
        <form:label path="startDate">Star date: </form:label>
        <form:input cssClass="form-control"  path="startDate" placeholder="${project.startDate}"/>
    </div>
    <div class="form-group">
        <form:label path="finishDate">Finish Date: </form:label>
        <form:input cssClass="form-control"  path="finishDate" placeholder="${project.finishDate}"/>
    </div>
    <div class="form-group">
        <form:label path="description">Description: </form:label>
        <form:input cssClass="form-control" path="description" placeholder="${project.description}"/>
    </div>
    <div class="form-group">
        <form:label path="budget">Budget: </form:label>
        <form:input cssClass="form-control" path="budget" placeholder="${project.budget}"/>
    </div>
<input type="hidden" name="projectID"  value="${projectID}">
<input type="submit" class="btn btn-primary" value="Save"/>
</form:form>
</body>
</html>
