<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>

<body>
<h2>Welcome, ${user.firstname} ${user.lastname} </h2>

<a href="<c:url value="/profile/userdetails?userID=${user.userID}"/>">View Profile</a>
<a href="<c:url value="/profile/edit"/>">Edit Profile</a>

</body>
</html>
