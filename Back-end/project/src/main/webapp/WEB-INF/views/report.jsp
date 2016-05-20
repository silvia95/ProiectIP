<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/20/2016
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Generate Report</title>
</head>
<body>

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

        <input type="submit" value="Generate Report">
    </form:form>

<c:if test="${articlesInfo.size() > 0}">
    <h3>Report View</h3>
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
        <c:forEach items="${articlesInfo}" var="article" >
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
</body>
</html>
