<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: cristian
  Date: 17.05.2016
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search for</title>
</head>
<body>
<form:form action="/search/" method="post">
    <div>
        <label for="search">Search query:</label>
        <input id="search" type="text" name="s" placeholder="Search">
        <label for="search">Search type:</label>
        <select name="searchType">
            <option value="persoana">Nume persoana</option>
            <option value="citat">Citat</option>
            <option value="an">An</option>
        </select>
    </div>
    <input type="submit" value="Search"/>
</form:form>
</body>
</html>
