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
    <title>Edit Article</title>
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

        <section id="align-right">
            <form id="navbar-search" role="search">
                <section class="input-group">
                    <section class="input-group-btn">
                        <a href="/search/" class="btn btn-default">Search</a>

                    </section>
                </section>
            </form>
        </section>
    </section>

    <section id="da_big_section">
<form:form action="/edit/article" method="post" modelAttribute="article">
    <div class="form-group">
        <form:label path="title">Title: </form:label>
        <form:input cssClass="form-control" path="title" placeholder="${article.title}"/>
    </div>
    <div class="form-group">
        <form:label path="year">Year: </form:label>
        <form:input cssClass="form-control" path="year" placeholder="${article.year}"/>
    </div>
    <div class="form-group">
        <form:label path="journalISSN">Journal ISSN: </form:label>
        <form:input cssClass="form-control"  path="journalISSN" placeholder="${article.journalISSN}"/>
    </div>
    <input type="hidden" name="articleID"  value="${article.articleID}">
    <input type="submit" class="btn btn-primary" value="Save"/>
</form:form>

</body>
</html>