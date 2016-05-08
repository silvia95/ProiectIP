<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="<spring:url value="/resources/css/login/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/login/style.css"/>"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>

    <title> Login Form </title>


</head>
<body>
<!-- Top content -->
<div id="top-content2" style="display:none;  width: 100%; height: 500px;">

</div>
<div class="top-content">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1>Formular de logare</h1>
                    <img src="<spring:url value="/resources/img/logo-fii.png"/>">
                </div>
            </div>
            <div class="row">
                <div class="form-box form-content special">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Login</h3>
                            <p>Introduceti adresa de email si parola:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>
                    <div class="form-bottom test">
                        <form role="form" action="container.php" method="post" class="login-form">
                            <div class="form-group login-form">
                                <label class="sr-only" for="form-username">Email:</label>
                                <input type="email" name="form-username" placeholder="@info.uaic.ro..."
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group login-form">
                                <label class="sr-only" for="form-password">Parola</label>
                                <input type="password" name="form-password" placeholder="Password..."
                                       class="form-password form-control" id="form-password">
                            </div>
                            <button type="submit" class="btn">Logare</button>
                            </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
