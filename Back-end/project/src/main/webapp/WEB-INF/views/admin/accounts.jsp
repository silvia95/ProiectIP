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
    <title>Welcome</title>
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
        <a href="/admin/upload" class="btn btn-default">Upload Document</a>
        <a href="/admin/accounts" class="btn btn-default">Accounts</a>

        <a href="/logout" class="btn btn-default logout-btn">Logout</a>
    </section>

    <section id="da_big_section">

        <h3>Accounts</h3>

        <table class="table table-bordered" border="1">
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Type</th>
            </tr>
            <c:forEach items="${accounts}" var="account" >
                <tr>
                    <th>${account.email}</th>
                    <th>${account.firstname}</th>
                    <th>${account.lastname}</th>
                    <th>${account.type}</th>
                    <th><form action="/admin/account/delete" method="post"><input type="hidden" name="email" value="${account.email}"><input type="submit" value="Delete"></form></th>
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
