<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!-- Created By CodeWithNepal -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>CustomerProfile</title>
    <link rel="stylesheet" href="<c:url value="/css/customerProfile.css"/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>

<div>

    <div class=" p-3 px-md-4 d-flex flex-column align-items-center text-color-white   border-bottom shadow-sm p-3  flex-md-row"
         style="background-color : #f2f2f2">
        <div class="col row center">
            <div class="col row"><h5 class="my-0 mr-md-auto font-weight-normal">HomeServiceProvider</h5></div>
        </div>
        <div class="col"></div>
        <div class="col"><h2></h2></div>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Features</a>
            <a class="p-2 text-dark" href="#">Enterprise</a>
            <a class="p-2 text-dark" href="#">Support</a>
            <a class="p-2 text-dark" href="#">Pricing</a>
        </nav>
        <a href="<c:url value="/signOut"/>" class="btn btn-Dark">Logout</a>

    </div>
</div>
<div>
    <div class="cole-6">
        <div class="wrapper">
            <input type="checkbox" id="btn" hidden>
            <label for="btn" class="menu-btn">
                <i class="fas fa-bars"></i>
                <i class="fas fa-times"></i>
            </label>
            <nav id="sidebar">
                <div class="title">
                    dashboard
                </div>
                <ul class="list-items">
                    <li><a href="/"><i class="fas fa-home"></i>Home</a></li>
                    <li><a href="/customer/dashboard"><i class="fas fa-user"></i>Dashbord</a></li>
                    <li><a href="/customer/categoryList"><i class="fas fa-sliders-h"></i>CategoryList</a></li>
                    <li><a href="/customer/categoryList"><i class="fas fa-address-book"></i>Order</a></li>
                    <li><a href="/customer/orderHistory"><i class="fas fa-cog"></i>order history</a></li>
                    <li><a href="/customer/orderList"><i class="fas fa-stream"></i>See Orders</a></li>
                    <li><a href="#"><i class="fas fa-globe-asia"></i>Languages</a></li>
                    <li><a href="#"><i class="fas fa-envelope"></i>Contact us</a></li>

                </ul>
            </nav>
        </div>

    </div>
    <div class="cole-6">
        <div class=" text-center" style="padding : 60px 300px 0px 300px">
            <h1>Category List</h1>
            <table class="table table-bordered table-striped text-info">
                <tr>
                    <th>title</th>
                    <th>SubCategory</th>
                </tr>
                <c:forEach var="category" items="${list}">
                    <tr>
                        <td>${category.title}</td>

                        <td>
                            <a href="<c:url value="/customer/showSubCategory/${category.title}"/>">Show SubCategorys</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>

        </div>
    </div>

</div>
</body>
</html>













