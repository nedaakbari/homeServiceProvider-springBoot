<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/static/css/login.css"/>">

</head>
<body>
<div class="container">
    <input type="checkbox" id="flip">
    <div class="cover">
        <div class="front">
            <!--<img src="images/frontImg.jpg" alt="">-->
            <img src="<c:url value="/static/image/frontImg.jpg"/> ">
            <div class="text">
                <span span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Trying to satisfy you</span>
            </div>
        </div>
        <div class="back">
            <!--<img class="backImg" src="images/backImg.jpg" alt="">-->
            <img src="<c:url value="/static/image/backImg.jpg"/> ">
            <div class="text">
                <span span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Trying to satisfy you</span>
            </div>
        </div>
    </div>
    <div class="forms">
        <div class="form-content">
            <div class="login-form">
                <div class="title">Login</div>
                <form:form modelAttribute="userDto" action="/login" method="post">
                    <p class="text-danger" style="text-color:red !important;">${error}</p>
                    <div class="input-boxes">
                        <div class="input-box">
                            <i class="fas fa-envelope"></i>
                            <form:input type="username" path="username" name="username" placeholder="Enter your email"/>
                            <br/>
                            <form:errors path="username" cssClass="text-danger"/>
                        </div>
                        <div class="input-box">
                            <i class="fas fa-lock"></i>
                            <form:input type="password" path="password" name="password"
                                        placeholder="Enter your password"/>
                            <br/>
                            <form:errors path="password" cssClass="text-danger"/>
                        </div>

                        <div class="button input-box">
                            <input type="submit" name="customerLogin" >
                        </div>
                        <div class="text sign-up-text">
                            Don't have an account? <label <%--for="flip"--%>>
                            <a href="<c:url value="/register"/>">Sigup now</a>
                        </label>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>

</html>
