<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <form:form action="/report" method="post" modelAttribute="reportForm">
        <label>
            <input type="checkbox" name="productionCheckBox">
        </label>Scientific Production
        <div>
            <form:label path="scientificProduction.name">Article Name: </form:label>
            <form:input path="scientificProduction.name"/>
        </div>
        <div>
            <form:label path="scientificProduction.authorsText">Authors: </form:label>
            <form:input path="scientificProduction.authorsText"/>
        </div>
        <div>
            <form:label path="scientificProduction.journalName">Journal Name: </form:label>
            <form:input path="scientificProduction.journalName"/>
        </div>
        <div>
            <form:label path="scientificProduction.classification">Classification: </form:label>
            <form:input path="scientificProduction.classification"/>
        </div>
        <div>
            <form:label path="scientificProduction.articleType">Article Type: </form:label>
            <form:input path="scientificProduction.articleType"/>
        </div>
        <div>
            Score
            <form:label path="scientificProduction.fromScore">From: </form:label>
            <form:input path="scientificProduction.fromScore"/>
            <form:label path="scientificProduction.toScore">To: </form:label>
            <form:input path="scientificProduction.toScore"/>
        </div>
        <div>
            Year
            <form:label path="scientificProduction.fromYear">From: </form:label>
            <form:input path="scientificProduction.fromYear"/>
            <form:label path="scientificProduction.toYear">To: </form:label>
            <form:input path="scientificProduction.toYear"/>
        </div>

        <br>

        <label>
            <input type="checkbox" name="impactCheckBox">
        </label>Scientific Impact
        <div>
            <form:label path="scientificImpact.name">Article Name: </form:label>
            <form:input path="scientificImpact.name"/>
        </div>
        <div>
            <form:label path="scientificImpact.authorsText">Authors: </form:label>
            <form:input path="scientificImpact.authorsText"/>
        </div>
        <div>
            <form:label path="scientificImpact.journalName">Journal Name: </form:label>
            <form:input path="scientificImpact.journalName"/>
        </div>
        <div>
            <form:label path="scientificImpact.classification">Classification: </form:label>
            <form:input path="scientificImpact.classification"/>
        </div>
        <div>
            <form:label path="scientificImpact.articleType">Article Type: </form:label>
            <form:input path="scientificImpact.articleType"/>
        </div>
        <div>
            Score
            <form:label path="scientificImpact.fromScore">From: </form:label>
            <form:input path="scientificImpact.fromScore"/>
            <form:label path="scientificImpact.toScore">To: </form:label>
            <form:input path="scientificImpact.toScore"/>
        </div>
        <div>
            Year
            <form:label path="scientificImpact.fromYear">From: </form:label>
            <form:input path="scientificImpact.fromYear"/>
            <form:label path="scientificImpact.toYear">To: </form:label>
            <form:input path="scientificImpact.toYear"/>
        </div>

        <input type="submit" value="Generate Report">
    </form:form>

<c:if test="${articlesProduction.size() > 0}">
    <h3>Scientific Production</h3>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Year</th>
            <th>JournalISSN</th>
            <th>Journal Name</th>
            <th>Score</th>
            <th>Authors</th>
            <th>Other Authors</th>
        </tr>
        <c:forEach items="${articlesProduction}" var="article" >
            <tr>
                <th>${article.title}</th>
                <th>${article.year}</th>
                <th>${article.journalISSN}</th>
                <th>${article.journalTitle}</th>
                <th>${article.score}</th>
                <th><c:forEach items="${article.authors}" var="author" varStatus="loop">${author.firstname} ${author.lastname}<c:if test="${!loop.last}">,</c:if> </c:forEach></th>
                <th><c:forEach items="${article.otherAuthors}" var="author" varStatus="loop">${author}<c:if test="${!loop.last}">,</c:if> </c:forEach></th>
            </tr>
        </c:forEach>
    </table>
</c:if>


    <c:if test="${articlesImpact.size() > 0}">
        <h3>Scientific Impact</h3>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Year</th>
                <th>JournalISSN</th>
                <th>Journal Name</th>
                <th>Score</th>
                <th>Authors</th>
                <th>Other Authors</th>
            </tr>
            <c:forEach items="${articlesImpact}" var="article" >
                <tr>
                    <th>${article.title}</th>
                    <th>${article.year}</th>
                    <th>${article.journalISSN}</th>
                    <th>${article.journalTitle}</th>
                    <th>${article.score}</th>
                    <th><c:forEach items="${article.authors}" var="author" varStatus="loop">${author.firstname} ${author.lastname}<c:if test="${!loop.last}">,</c:if> </c:forEach></th>
                    <th><c:forEach items="${article.otherAuthors}" var="author" varStatus="loop">${author}<c:if test="${!loop.last}">,</c:if> </c:forEach></th>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    </section>

    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>

        <p id="fii">Facultatea de Informatica</p>
    </section>
    </section>

</body>
</html>
