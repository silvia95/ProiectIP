<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: cristian
  Date: 26.05.2016
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Centralized data</title>
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
        <div class="info">
            <h3>Titular: ${cent.teacher.firstname} ${cent.teacher.lastname}</h3>
            <h3>Functia: ${cent.getActualType()}</h3>
            <h3>Raportare la pozitia de: ${cent.getFutureType()}</h3>
        </div>
        <table class="table table-bordered" border="1">
            <tr>
                <th>Perspective</th>
                <th>Subperspective</th>
                <th>Minim</th>
                <th>Realizat</th>
                <th>Situatie</th>
            </tr>
            <tr>
                <td>b</td>
                <td>Publicatii de tip A+B</td>
                <td>${cent.minArticlesAB()}</td>
                <td>${cent.getArticlesABScore()}</td>
                <td>${cent.passArticlesAB()}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>Publicatii de tip A+B+C</td>
                <td>${cent.minArticlesTotal()}</td>
                <td>${cent.getArticlesTotalScore()}</td>
                <td>${cent.passArticlesTotal()}</td>
            </tr>
            <tr>
                <td>c</td>
                <td>Citari de tip A+B</td>
                <td>${cent.minQuotationsAB()}</td>
                <td>${cent.getQuotationsABScore()}</td>
                <td>${cent.passQuotationsAB()}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>Citari de tip A+B+C+D</td>
                <td>${cent.minQuotationsTotal()}</td>
                <td>${cent.getQuotationsTotalScore()}</td>
                <td>${cent.passQuotationsTotal()}</td>
            </tr>
        </table>
    </section>
    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>
        <p id="fii">Facultatea de Informatica</p>
    </section>
</section>
</body>
</html>
