<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Register</title>  <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="/static/css/login.css"/>">
</head>

<body>
<div class="container">
    <input type="checkbox" id="flip">
    <div class="cover">
        <div class="front">
            <img src="<c:url value="/static/image/frontImg.jpg"/> ">
            <div class="text">
                <span class="text-1">We Are Glad To  <br> Having You</span>
                <span class="text-2">Try to satisfy you</span>
            </div>
        </div>
        <div class="back">
            <img src="<c:url value="/static/image/backImg.jpg"/> ">
            <div class="text">
                <span class="text-1">Complete miles of journey <br> with one step</span>
                <span class="text-2">Let's get started</span>
            </div>
        </div>
    </div>
    <div class="forms">
        <div class="form-content">
            <div class="signup-form">
                <div class="title">Signup</div>
                <form:form modelAttribute="user" enctype="multipart/form-data" action="register"
                           method="post">
                    <div class="input-boxes">
                        <div>
                            Expert <form:radiobutton path="role" value="EXPERT" onclick="javascript:yesnoCheck();"
                                                     name="yesno" id="yesCheck"/>
                            Customer <form:radiobutton path="role" value="CUSTOMER" onclick="javascript:yesnoCheck();"
                                                       name="yesno" id="noCheck"/>
                        </div>

                    </div>

                    <div class="input-box">
                        <i class="fas fa-user"></i>
                        <form:input type="text" path="firstName" name="firstName"
                                    placeholder="Enter your firstName"/>

                    </div>
                    <div class="input-box-error text-danger" style="font-size: 12px;color:red;!important;">
                        <form:errors path="firstName" cssClass="text-danger;!important"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-user"></i>
                        <form:input type="text" path="lastName" name="lastName"
                                    placeholder="Enter your lastName"/>

                    </div>
                    <div class="input-box-error" style="font-size: 12px;color:red;!important;">
                        <form:errors path="lastName" cssClass="text-danger" />
                    </div>
                    <div class="input-box">
                        <i class="fas fa-user"></i>
                        <form:input type="text" path="username" name="userName"
                                    placeholder="Enter your userName"/>

                    </div>
                    <div class="input-box-error" style="font-size: 12px;color:red;!important;">
                        <form:errors path="username" cssClass="text-danger"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-envelope"></i>
                        <form:input type="text" path="email" name="email" placeholder="Enter your email"/>
                        <p class="text-danger" style="color:red;!important">${error}</p>

                    </div>
                    <div class="input-box-error" style="font-size: 12px;color:red;!important;">
                        <form:errors path="email" cssClass="text-danger"/>
                    </div>
                    <div class="input-box">
                        <i class="fas fa-lock"></i>
                        <form:input type="text" path="password" name="password"
                                    placeholder="Enter your password"/>
                    </div>
                    <div class="input-box-error" style="font-size: 12px;color:red;!important;">
                        <form:errors path="password" cssClass="text-danger;!important"/>
                    </div>

                    <div id="ifYes" style="visibility:hidden">
                        <input type="file" id="image" name="image" <%--required="false"--%>>
                    </div>
                    <div class="button input-box">
                        <input type="submit" name="register">
                    </div>

                    <div class="text sign-up-text">Already have an account? <label <%--for="flip"--%>>
                        <a href="<c:url value="/login"/>">Login now</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<%--<script src="<c:url value="/static/js/register.js"/>"></script>--%>
<script>


    function yesnoCheck() {
        if (document.getElementById('yesCheck').checked) {
            document.getElementById('ifYes').style.visibility = 'visible';
            const imageFile = document.getElementById("image");

            imageFile.onchange = function () {
                const maxAllowedSize = 300 * 1024;
                if (this.files[0].size > maxAllowedSize) {
                    alert("Image File is too big!");
                    this.value = "";
                }
                if (this.files[0]==null|| this.files[0].value===0) {
                    alert("upload image!");
                    this.value = "";
                }
            }
        } else {
            document.getElementById('ifYes').style.visibility = 'hidden';
        }
    }


</script>
</body>

