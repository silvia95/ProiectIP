 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrei
  Date: 5/10/2016
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Activity</title>
</head>
<body>
<h3>My Articles</h3>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>Journal ISSN</th>
    </tr>
    <c:forEach items="${articleList}" var="article" >
        <tr>
            <td>${article.title}</td>
            <td>${article.year}</td>
            <td><a href="/scientific/journaldetails?journalISSN=${article.journalISSN}"> ${article.journalISSN} </a></td>
            <td><a href="/edit/article?articleID=${article.articleID}" >Edit</a></td>
        </tr>
    </c:forEach>
</table>

<a href="/scientific/myactivity/addArticle"> <input type="submit" value="Add Article"></a>



<h3>My Quotations</h3>
<table border="1">
    <tr>
        <th>Article Name</th>
        <th>Year</th>
        <th>Text</th>
        <th>Location</th>
        <th>Authors</th>
    </tr>
    <c:forEach items="${quotationList}" var="quotation" >
        <tr>
            <td>${quotation.articleName}</td>
            <td>${quotation.year}</td>
            <td>${quotation.text}</td>
            <td>${quotation.location}</td>
            <td>${quotation.authors}</td>
            <td><a href="/edit/quotation?articleID=${quotation.articleID}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

<form action="/scientific/myactivity/addQuotation" method="get">
    <select name="articleID">
        <c:forEach items="${articleList}" var="article">
            <option value="${article.articleID}">${article.title}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Add Quotation">
</form>


<h3>My Projects</h3>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Director</th>
        <th>Domain</th>
        <th>Star date</th>
        <th>Finish Date</th>
        <th>Description</th>
        <th>Budget</th>
    </tr>
    <c:forEach items="${projectList}" var="project" >
        <tr>
            <th>${project.title}</th>
            <th>${project.director}</th>
            <th>${project.domain}</th>
            <th>${project.startDate}</th>
            <th>${project.finishDate}</th>
            <th>${project.description}</th>
            <th>${project.budget}</th>
            <td><a href="/edit/project?projectID=${project.projectID}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/scientific/myactivity/addProject"> <input type="submit" value="Add Project"></a>

<h3>My Conferences</h3>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Location</th>
        <th>Year</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${conferenceList}" var="conference" >
        <tr>
            <th>${conference.name}</th>
            <th>${conference.location}</th>
            <th>${conference.year}</th>
            <th>${conference.details}</th>
        </tr>
    </c:forEach>
</table>


</body>
</html>
