<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!-- Created By CodeWithNepal -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>rat </title>
    <link rel="stylesheet" href="<c:url value="/static/css/rating.css"/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

</head>
<body>

<div class="container">
    <p class="text-danger" style="text-color:red !important;">${error}</p>
    <div class="star-widget">
        <input type="radio" name="rate" id="rate-5" value="5.0">
        <label for="rate-5" class="fas fa-star" ></label>
        <input type="radio" name="rate" id="rate-4" value="4.0">
        <label for="rate-4" class="fas fa-star"></label>
        <input type="radio" name="rate" id="rate-3" value="3.0">
        <label for="rate-3" class="fas fa-star"></label>
        <input type="radio" name="rate" id="rate-2" value="2.0">
        <label for="rate-2" class="fas fa-star"></label>
        <input type="radio" name="rate" id="rate-1" value="1.0">
        <label for="rate-1" class="fas fa-star"></label>

        <form:form action="/customer/placeScore" method="post">
            <header>

            </header>
            <div class="textarea">
                <textarea name="comment" cols="30" placeholder="comment for act of expert.."></textarea>
            </div>
            <div class="btn">
                <button type="submit">Post</button>
            </div>
        </form:form>
    </div>
</div>
<script>
    const btn = document.querySelector("button");
    const post = document.querySelector(".post");
    const widget = document.querySelector(".star-widget");
    const editBtn = document.querySelector(".edit");

</script>

</body>
</html>

