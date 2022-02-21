<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="resources/css/profileStyle.css" rel="stylesheet">
    <link href="resources/css/mainServiceStyle.css" rel="stylesheet">

</head>
<body>
<h1>SubService List</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Title</th>
        <th scope="col">basePrice</th>
        <th scope="col">description</th>
        <th scope="col">choice</th>
<%--        <th scope="col">mainServiceName</th>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sub" items="${list}">
        <tr>
            <td>${sub.title}</td>
            <td>${sub.basePrice}</td>
            <td>${sub.description}</td>
            <td>
                <a href="<c:url value="/addingSub/${sub.title}"/>">adding to your specialty</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
