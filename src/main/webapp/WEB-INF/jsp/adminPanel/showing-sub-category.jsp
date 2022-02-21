<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showingSubCategory</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container" style="margin-top: 150px">
    <div>

            Select a Category:&nbsp;
            <select name="category">
                <c:forEach items="${categoryList}" var="categoryTitle">
                    <option value="${categoryTitle.title}">${categoryTitle.title}</option>
                </c:forEach>
            </select>


    </div>
    <div>
        <a href="<c:url value="/admin/showingSubCategory"/>" class="btn btn-dark ">show all subCategory</a>
    </div>


    <table class="table table-bordered table-striped  text-black text-center" style="padding: 100px 100px 0px 100px ;!important;">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">basePrice</th>
            <th scope="col">description</th>
            <th scope="col">action</th>
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
                    <a href="<c:url value="/admin/edit-subcategory/${sub.title}" />" class="btn btn-dark">edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
