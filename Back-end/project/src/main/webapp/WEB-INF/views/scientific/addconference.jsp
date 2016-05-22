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
    <title>Title</title>
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

        <a href="/search/" class="btn btn-default search-btn">Search</a>
    </section>

    <section id="da_big_section">
<form:form action="/scientific/myactivity/addConference" method="post" modelAttribute="conference">
    <div class="form-group">
        <form:label path="name">Name: </form:label>
        <form:input cssClass="form-control" id="name" path="name"/>
    </div>
    <div class="form-group">
        <form:label path="location">Location: </form:label>
        <form:input cssClass="form-control" id="year" path="location"/>
    </div>
    <div class="form-group">
        <form:label path="year">Year: </form:label>
        <form:input cssClass="form-control" id="year" path="year"/>
    </div>
    <div class="form-group">
        <form:label path="details">Details: </form:label>
        <form:input cssClass="form-control" id="details" path="details"/>
    </div>
    <input type="hidden" name="conferenceID" value=${conference.conferenceID}>
    <input type="submit" class="btn btn-primary" value="Add Quotation"/>
</form:form>
    </section>

    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>

        <p id="fii">Facultatea de Informatica</p>
    </section>
</section>

</body>
</html>