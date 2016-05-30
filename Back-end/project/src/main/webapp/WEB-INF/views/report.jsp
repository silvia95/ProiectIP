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

        <a href="/logout" class="btn btn-default logout-btn">Logout</a>
        <a href="/search/" class="btn btn-default search-btn">Search</a>
    </section>

    <section id="da_big_section">

        <form:form action="/report" method="post" modelAttribute="reportForm">
            <div class="form-group">
                <label>
                    <input type="checkbox" name="centralizeCheckBox">
                </label>Centralize
            </div>
            <div class="form-group">
                <label>
                    <input type="checkbox" name="productionCheckBox">
                </label>Scientific Production
            </div>
            <div class="form-group">
                <form:label path="scientificProduction.name">Article Name: </form:label>
                <form:input cssClass="form-control" path="scientificProduction.name"/>
            </div>
            <div class="form-group">
                <form:label path="scientificProduction.authorsText">Authors: </form:label>
                <form:input cssClass="form-control" path="scientificProduction.authorsText"/>
            </div>
            <div class="form-group">
                <form:label path="scientificProduction.journalName">Journal Name: </form:label>
                <form:input cssClass="form-control" path="scientificProduction.journalName"/>
            </div>
            <div class="form-group">
                <form:label path="scientificProduction.classification">Classification: </form:label>
                <form:input cssClass="form-control" path="scientificProduction.classification"/>
            </div>
            <div class="form-group">
                <form:label path="scientificProduction.articleType">Article Type: </form:label>
                <form:input cssClass="form-control" path="scientificProduction.articleType"/>
            </div>
            <div class="form-group">
                <span class="dual-title">Year</span>
                <div class="half">
                    <form:label path="scientificProduction.fromScore">From: </form:label>
                    <form:input cssClass="form-control" path="scientificProduction.fromScore"/>
                </div>
                <div class="half">
                    <form:label path="scientificProduction.toScore">To: </form:label>
                    <form:input cssClass="form-control" path="scientificProduction.toScore"/>
                </div>
            </div>
            <div class="form-group">
                <span class="dual-title">Year</span>
                <div class="half">
                    <form:label path="scientificProduction.fromYear">From: </form:label>
                    <form:input cssClass="form-control" path="scientificProduction.fromYear"/>
                </div>
                <div class="half">
                    <form:label path="scientificProduction.toYear">To: </form:label>
                    <form:input cssClass="form-control" path="scientificProduction.toYear"/>
                </div>
            </div>

            <br><br><br>
            <br><br><br>

            <label>
                <input type="checkbox" name="impactCheckBox">Scientific Impact
            </label>
            <div class="form-group">
                <form:label path="scientificImpact.name">Article Name: </form:label>
                <form:input cssClass="form-control" path="scientificImpact.name"/>
            </div>
            <div class="form-group">
                <form:label path="scientificImpact.authorsText">Authors: </form:label>
                <form:input cssClass="form-control" path="scientificImpact.authorsText"/>
            </div>
            <div class="form-group">
                <form:label path="scientificImpact.journalName">Journal Name: </form:label>
                <form:input cssClass="form-control" path="scientificImpact.journalName"/>
            </div>
            <div class="form-group">
                <form:label path="scientificImpact.classification">Classification: </form:label>
                <form:input cssClass="form-control" path="scientificImpact.classification"/>
            </div>
            <div class="form-group">
                <form:label path="scientificImpact.articleType">Article Type: </form:label>
                <form:input cssClass="form-control" path="scientificImpact.articleType"/>
            </div>
            <div class="form-group">
                <span class="dual-title">Score</span>
                <div class="half">
                    <form:label path="scientificImpact.fromScore">From: </form:label>
                    <form:input cssClass="form-control" path="scientificImpact.fromScore"/>
                </div>
                <div class="half">
                    <form:label path="scientificImpact.toScore">To: </form:label>
                    <form:input cssClass="form-control" path="scientificImpact.toScore"/>
                </div>
            </div>
            <div class="form-group">
                <span class="dual-title">Year</span>
                <div class="half">
                    <form:label path="scientificImpact.fromYear">From: </form:label>
                    <form:input cssClass="form-control" path="scientificImpact.fromYear"/>
                </div>
                <div class="half">
                    <form:label path="scientificImpact.toYear">To: </form:label>
                    <form:input cssClass="form-control" path="scientificImpact.toYear"/>
                </div>
            </div>

            <input type="submit" class="btn btn-primary" value="Generate Report">
        </form:form>

        <c:if test="${cent != null}">
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
                <tr>
                    <td>d</td>
                    <td></td>
                    <td>36</td>
                    <td>${cent.performanceScore}</td>
                    <td>${cent.passPerformance()}</td>
                </tr>
            </table>

        </c:if>

        <c:if test="${articlesProduction.size() > 0}">
            <h3>Scientific Production</h3>
            <table class="table table-bordered" border="1">
                <tr>
                    <th>Title</th>
                    <th>Year</th>
                    <th>JournalISSN</th>
                    <th>Journal Name</th>
                    <th>Score</th>
                    <th>Authors</th>
                    <th>Other Authors</th>
                </tr>
                <c:forEach items="${articlesProduction}" var="article">
                    <tr>
                        <th>${article.title}</th>
                        <th>${article.year}</th>
                        <th>${article.journalISSN}</th>
                        <th>${article.journalTitle}</th>
                        <th>${article.score}</th>
                        <th><c:forEach items="${article.authors}" var="author"
                                       varStatus="loop">${author.firstname} ${author.lastname}<c:if
                                test="${!loop.last}">,</c:if> </c:forEach></th>
                        <th><c:forEach items="${article.otherAuthors}" var="author" varStatus="loop">${author}<c:if
                                test="${!loop.last}">,</c:if> </c:forEach></th>
                    </tr>
                </c:forEach>
            </table>
        </c:if>


        <c:if test="${articlesImpact.size() > 0}">
            <h3>Scientific Impact</h3>

            <c:forEach items="${articlesImpact}" var="article">
                <h3>Article Details</h3>
                <table class="table table-bordered" border="1">
                    <tr>
                        <th>Title</th>
                        <th>Year</th>
                        <th>JournalISSN</th>
                        <th>Journal Name</th>
                        <th>Score</th>
                        <th>Authors</th>
                        <th>Other Authors</th>
                    </tr>
                    <tr>
                        <th>${article.title}</th>
                        <th>${article.year}</th>
                        <th>${article.journalISSN}</th>
                        <th>${article.journalTitle}</th>
                        <th>${article.score}</th>
                        <th><c:forEach items="${article.authors}" var="author"
                                       varStatus="loop">${author.firstname} ${author.lastname}<c:if
                                test="${!loop.last}">,</c:if> </c:forEach></th>
                        <th><c:forEach items="${article.otherAuthors}" var="author" varStatus="loop">${author}<c:if
                                test="${!loop.last}">,</c:if> </c:forEach></th>
                    </tr>
                </table>
                <table class="table table-bordered" border="1">
                    <tr>
                        <th>Article Name</th>
                        <th>Year</th>
                        <th>Text</th>
                        <th>Journal Name</th>
                        <th>Authors</th>
                    </tr>
                    <c:forEach items="${article.quotationList}" var="quotation">
                        <tr>
                            <td>${quotation.articleName}</td>
                            <td>${quotation.year}</td>
                            <td>${quotation.text}</td>
                            <td>${quotation.location}</td>
                            <td>${quotation.authorsText}</td>

                        </tr>
                    </c:forEach>
                </table>
            </c:forEach>
        </c:if>

    </section>

    <section id="footer_section">
        <p id="uaic">Universitatea "Alexandru Ioan Cuza" Iasi</p>

        <p id="fii">Facultatea de Informatica</p>
    </section>
</section>

</body>
</html>
