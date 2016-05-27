<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

        <a href="/logout" class="btn btn-default logout-btn">Logout</a>
        <a href="/search/" class="btn btn-default search-btn">Search</a>
    </section>

    <section id="da_big_section">

        <h3>My Articles</h3>
        <table class="table table-bordered" border="1">
            <tr>
                <th>Title</th>
                <th>Year</th>
                <th>Journal Title</th>
            </tr>
            <c:forEach items="${articleList}" var="article">
                <tr>
                    <td><a href="/scientific/articleDetails?articleID=${article.articleID}">${article.title}</a></td>
                    <td>${article.year}</td>
                    <td>
                        <a href="/scientific/journaldetails?journalISSN=${article.journalISSN}"> ${article.journalTitle} </a>
                    </td>
                    <td><a href="/edit/article?articleID=${article.articleID}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>

        <a href="/scientific/myactivity/addArticle"> <input class="btn btn-primary" type="submit"
                                                            value="Add Article"></a>


        <form action="/scientific/myactivity/addQuotation" method="get">
            <select class="form-control" name="articleID">
                <c:forEach items="${articleList}" var="article">
                    <option value="${article.articleID}">${article.title}</option>
                </c:forEach>
            </select>
            <input type="submit" class="btn btn-primary" value="Add Quotation">
        </form>

        <h3>My Projects</h3>
        <c:choose>
            <c:when test="${!empty projectList}">
                <table class="table table-bordered" border="1">
                    <tr>
                        <th>Title</th>
                        <th>Director</th>
                        <th>Domain</th>
                        <th>Star date</th>
                        <th>Finish Date</th>
                        <th>Description</th>
                        <th>Budget</th>
                    </tr>
                    <c:forEach items="${projectList}" var="project">
                        <tr>
                            <th><a href="/scientific/projectDetails?projectID=${project.projectID}">${project.title}</a>
                            </th>
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
            </c:when>
            <c:otherwise>
                <h5>No projects yet</h5>
            </c:otherwise>
        </c:choose>
        <a href="/scientific/myactivity/addProject"> <input type="submit" class="btn btn-primary"
                                                            value="Add Project"></a>

        <h3>My Conferences</h3>
        <c:choose>
            <c:when test="${!empty projectList}">
                <table class="table table-bordered" border="1">
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Year</th>
                        <th>Details</th>
                    </tr>
                    <c:forEach items="${conferenceList}" var="conference">
                        <tr>
                            <th>${conference.name}</th>
                            <th>${conference.location}</th>
                            <th>${conference.year}</th>
                            <th>${conference.details}</th>
                            <td><a href="/edit/conference?conferenceID=${conference.conferenceID}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h5>No conferences yet</h5>
            </c:otherwise>
        </c:choose>

        <h3>My Scientific Events</h3>
        <c:choose>
            <c:when test="${!empty scientificEvents}">
                <table class="table table-bordered" border="1">
                    <tr>
                        <th>Name</th>
                        <th>Year</th>
                        <th>Link</th>
                        <th>Score</th>
                    </tr>
                    <c:forEach items="${scientificEvents}" var="event">
                        <tr>
                            <th>${event.name}</th>
                            <th>${event.year}</th>
                            <th>${event.link}</th>
                            <th>${event.score}</th>
                            <td><a href="/edit/event?eventID=${conference.conferenceID}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h5>No Scientific Events yet</h5>
            </c:otherwise>
        </c:choose>
        <a href="/scientific/myactivity/addEvent"> <input type="submit" class="btn btn-primary"
                                                               value="Add Event"></a>

    </section>

    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>

        <p id="fii">Facultatea de Informatica</p>
    </section>
</section>

</body>
</html>
