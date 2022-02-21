<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/static/css/adminPanel.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Pagination Users</title>
    <style>

    </style>
</head>
<body>

<%
    Object admin = session.getAttribute("admin");
%>
<c:if test="${sessionScope.admin eq null}">
    <h1 class="display-5 text-center pt-5">Access Denied</h1>
    <a href="/admin">Login to post</a>
</c:if>

<c:if test="${sessionScope.admin !=null}">


    <div class="wrapper">
        <!--Top menu -->
        <div class="section">
            <div class="top_navbar">
                <div class="hamburger">
                    <a href="#">
                        <i class="fas fa-bars"></i>
                    </a>
                    <button href="<c:url value="/logout"/>" class="btn btn-outline-light" type="button"
                            style="margin-left: 970px">Logout</button>
                </div>
            </div>

        </div>

        <div class="sidebar">
            <div class="profile">
                <img src=https://ath2.unileverservices.com/wp-content/uploads/sites/4/2020/02/IG-annvmariv-1024x1016.jpg
                     alt="profile_picture">
                <h3> hello ${name}</h3>
                <p>Admin</p>
            </div>
            <!--menu item-->

            <ul>
                <li>
                    <a href="<c:url value="/"/>" class="active">
                        <span class="icon"><i class="fas fa-home"></i></span>
                        <span class="item">Home</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/adminDashboard"/>">
                        <span class="icon"><i class="fas fa-desktop"></i></span>
                        <span class="item">My Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/admin/mangeCategory"/>">
                        <span class="icon"><i class="fas fa-user-friends"></i></span>
                        <span class="item">ManageCategory</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/manageExperts"/>">
                        <span class="icon"><i class="fas fa-tachometer-alt"></i></span>
                        <span class="item">ManageExperts</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/verifyUsers"/>">
                        <span class="icon"><i class="fas fa-database"></i></span>
                        <span class="item">verifyUsers</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/reportUsers"/>">
                        <span class="icon"><i class="fas fa-chart-line"></i></span>
                        <span class="item">ReportsUser</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/users"/>">
                        <span class="icon"><i class="fas fa-search"></i></span>
                        <span class="item">searchUsers</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/searchExperts"/>">
                        <span class="icon"><i class="fas fa-cog"></i></span>
                        <span class="item">searchExperts</span>
                    </a>
                </li>
            </ul>
        </div>

                <table class="table table-bordered" style="font-size:15px">
                    <tr>
                        <th width="80px">firstName</th>
                        <th width="80px">LastName</th>
                        <th width="80px">email</th>
                        <th width="80px">role</th>
                        <td width="80px"> </td>
                    </tr>
                    <c:forEach items="${pagedListHolder.pageList}" var="item">
                        <tr>
                            <td>${item.firstName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.email}</td>
                            <td>${item.role}</td>
                            <td>
                                <a href="detail/${item.email}">details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <script>
        var hamburger = document.querySelector(".hamburger");
        hamburger.addEventListener("click", function () {
            document.querySelector("body").classList.toggle("active");
        })
    </script>
</c:if>
</body>

</html>





<div class="container my-2">
    <h1>Employees List</h1>
    <a href="<c:url value="/showNewEmployeeForm"/>" class="btn btn-primary btn-sm mb-3">Add Employee</a>

    <table border="1" class="table table-striped table-responsive-md">
        <thead>
        <tr>
            <th>
                <a href="<c:url value="/page/${currentPage} + '?sortField=firstName&sortDir=' + ${reverseSortDir}"/>"
                   class="btn btn-primary btn-sm mb-3">Employee First Name</a>
            </th>
            <th>
                <a href="<c:url value="/page/${currentPage} + '?sortField=lastName&sortDir=' + ${reverseSortDir}"/>"
                   class="btn btn-primary btn-sm mb-3">Employee Last Name</a>

            </th>
            <th>
                <a href="<c:url value="/page/${currentPage} + '?sortField=email&sortDir=' + ${reverseSortDir}"/>"
                   class="btn btn-primary btn-sm mb-3">Employee Email</a>

                <%--<a th:href="@{'/page/' + ${currentPage} + '?sortField=email&sortDir=' + ${reverseSortDir}}">--%>

            </th>
            <th> Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listEmployees}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
        </tr>
        </tbody>
    </table>
    </c:forEach>

   <%-- <div
    <c:if test="${totalPages > 1}"/>
    <div class="row col-sm-10">
        <div class="col-sm-2">
            Total Rows: ${totalItems}
        </div>
        <div class="col-sm-1">
            <span
            <c:forEach items="${#numbers.sequence(1, totalPages)}">
            <a <c:if test="${currentPage != i}"/>
                    href="<c:url value="/page/' + ${i}+ '?sortField=' + ${sortField} + '&sortDir=' + ${sortDir}"/>">[[${i}]]</a>
            <span
                    <c:otherwise>
                        ${currentPage != i}
                    </c:otherwise>${i}
            </span>
            </span>
        </div>
        <div class="col-sm-1">
            <a  <c:if test="${currentPage < totalPages}"/>
                        <c:url value="/page/' + ${currentPage + 1}+ '?sortField=' + ${sortField} + '&sortDir=' + ${sortDir}"/>Next</a>
            <span <c:otherwise>
                ${currentPage < totalPages}
            </c:otherwise>Next</span>
        </div>

        <div class="col-sm-1">
            <a <c:if test="${currentPage < totalPages}"/>
                        <c:url value="/page/' + ${totalPages}+ '?sortField=' + ${sortField} + '&sortDir=' + ${sortDir}"/>Last</a>
            <span  <c:otherwise>
                ${currentPage < totalPages}
                    </c:otherwise>Last</span>
        </div>
    </div>
</div>

</c:forEach>--%>
</div>
</body>
</html>