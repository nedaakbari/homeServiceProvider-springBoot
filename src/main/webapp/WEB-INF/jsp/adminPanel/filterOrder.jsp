
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/adminPanel.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>searchUsers</title>

</head>
<body>

<%
    Object admin = session.getAttribute("admin");
%>
<c:if test="${sessionScope.admin eq null}">
    <h1 class="display-5 text-center pt-5">Access Denied</h1>
</c:if>
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
                <a href="<c:url value="/admin/dashboard"/>">
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
                <a href="<c:url value="/admin/manageExperts"/>">
                    <span class="icon"><i class="fas fa-tachometer-alt"></i></span>
                    <span class="item">ManageExperts</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/verifyUsers"/>">
                    <span class="icon"><i class="fas fa-database"></i></span>
                    <span class="item">verifyUsers</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/reportUsers"/>">
                    <span class="icon"><i class="fas fa-chart-line"></i></span>
                    <span class="item">ReportsUser</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/users"/>">
                    <span class="icon"><i class="fas fa-search"></i></span>
                    <span class="item">searchUsers</span>
                </a>
            </li>
            <li>
                <a href="<c:url value="/admin/searchExperts"/>">
                    <span class="icon"><i class="fas fa-search"></i></span>
                    <span class="item">searchExperts</span>
                </a>
            </li>
        </ul>
    </div>


    <%--body--%>
    <div class="">
        <div class="row mt-2">
                <form:form id="search-form" cssClass="m-5 p-5 text-center" cssStyle="width: 1100px"
                           action="/admin/ordersHistory" method="get">
                    <table id="userTbl" class="table table-striped  table-hover" style="margin-left: 180px"><%--table-dark--%>
                        <tr>
                            <td>
                               <%-- <form:input path="firstName" name="firstName" placeHolder="firstName"/>--%>
                                <input type="date" name="startDate" placeHolder="startDate"/>
                            </td>
                            <td>
                                <input type="date" name="endDate" placeHolder="endDate"/>
                            </td>

                            <td>
                                <label for="state">state:</label>

                                <select name="state" id="state">
                                    <option value="CUSTOMER">waiting for expert suggestion</option>
                                    <option value="EXPERT">waiting for select an expert</option>
                                    <option value="EXPERT">waiting for expert coming to your home</option>
                                    <option value="EXPERT">started</option>
                                    <option value="EXPERT">done</option>
                                    <option value="EXPERT">paid</option>
                                </select>
                            </td>
                            <td>
                                Select a subCategory:&nbsp;
                                <select name="category">
                                    <c:forEach items="${listSubCategory}" var="category">
                                        <option value="${category.title}">${category.title}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input type="submit" value="search"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="col">orderRegistrationDate</th>
                            <th scope="col"> subCategory title</th>
                            <th scope="col">description</th>
                            <th scope="col">score</th>
                            <th scope="col">doWorkDate</th>
                            <th scope="col">state</th>
                        </tr>
                        <c:forEach items="${ordersDtos}" var="order">
                            <tr>
                                <td>${order.orderRegistrationDate}</td>
                                <td>${order.subCategory.title}</td>
                                <td>${order.description}</td>
                                <td>${order.score}</td>
                                <td>${order.doWorkDate}</td>
                                <td>${order.state.toString()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form:form>

        </div>

    </div>

</div>

</div>
<script>
    var hamburger = document.querySelector(".hamburger");
    hamburger.addEventListener("click", function () {
        document.querySelector("body").classList.toggle("active");
    })
</script>

</body>

</html>
