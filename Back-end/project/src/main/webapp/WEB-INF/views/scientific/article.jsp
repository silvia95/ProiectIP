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
    <title>Article Details</title>
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

        <div><b>Title:</b> ${article.title}</div>
        <div><b>Journal Name:</b> ${article.journalTitle}</div>
        <div><b>Year:</b> ${article.year}</div>

        <h3>Authors</h3>

        <table class="table table-bordered" border="1">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:forEach items="${article.authors}" var="author" >
                <tr>
                    <td>${author.firstname}</td>
                    <td>${author.lastname}</td>
                </tr>
            </c:forEach>
            <c:forEach items="${article.others}" var="author" >
                <tr>
                    <td>${author.firstname}</td>
                    <td>${author.lastname}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>Quotations</h3>

        <table class="table table-bordered" border="1">
            <tr>
                <th>Article Name</th>
                <th>Year</th>
                <th>Text</th>
                <th>Journal Name</th>
                <th>Authors</th>
            </tr>
            <c:forEach items="${article.quotationList}" var="quotation" >
                <tr>
                    <td>${quotation.articleName}</td>
                    <td>${quotation.year}</td>
                    <td>${quotation.text}</td>
                    <td>${quotation.location}</td>
                    <td>${quotation.authorsText}</td>

                </tr>
            </c:forEach>
        </table>

    </section>

    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>

        <p id="fii">Facultatea de Informatica</p>
    </section>
</section>

</body>
</html>