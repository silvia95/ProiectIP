<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>

<body>
<h2>Welcome, ${teacherInfo.firstname} ${teacherInfo.lastname} </h2>

<a href="<c:url value="/profile/userdetails?userID=${teacherInfo.userID}"/>">View Profile</a>
<a href="<c:url value="/profile/edit"/>">Edit Profile</a> <br>

<a href="<c:url value="/scientific/myactivity"/>">My Activity</a> <br>
<a href="<c:url value="/scientific/articles"/>">Articles</a>

</body>
</html>
