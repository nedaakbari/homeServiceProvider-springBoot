<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!-- Created By CodeWithNepal -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>orderOfCustomer</title>
    <link rel="stylesheet" href="<c:url value="/static/css/customerProfile.css"/>">
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
    <div class="container row">
        <div class="cole-6"></div>
        <div class=" text-center cole-6" style="padding : 60px 300px 0px 300px">
            <h1>Order List</h1>
            <table class="table table-bordered table-striped" style="font-size: 15px;!important;">
                <thead>
                <tr class="bg-dark text-light">
                    <th scope="col">title</th>
                    <th scope="col">description</th>
                    <th scope="col">doWorkDate</th>
                    <th scope="col">state</th>
                    <th scope="col">address</th>
                    <th scope="col">offers</th>
                    <th scope="col">comment</th>
                    <th scope="col">payWith       :</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderOfCustomer}">
                    <tr>
                        <td>${order.subCategory.title}</td>
                        <td>${order.description}</td>
                        <td>${order.doWorkDate}</td>
                        <td>${order.state.name}</td>
                        <td>${order.address}</td>

                            <%--<td hidden>${order.codeNumber}</td>--%>
                        <td class="text-muted">
                            <c:choose>
                                <c:when test="${order.state=='WAITING_FOR_SELECT_AN_EXPERT'}">
                                    <a href="<c:url value="/customer/seeAllOfferAnOrder/${order.codeNumber}"/>">see offers</a>
                                </c:when>
                                <c:otherwise>
                                    see offers
                                </c:otherwise>
                            </c:choose>

                        </td>
                        <td class="text-muted">
                                <%--<a href="<c:url value="/seeAllOfferAnOrder/${order.codeNumber}"/>">see</a>--%>
                            <c:choose>
                                <c:when test="${order.state=='DONE'}">
                                    <a href="<c:url value="/customer/placeScore/${order.codeNumber}"/>">comment for this</a>
                                </c:when>
                                <c:otherwise>
                                    comment for this
                                </c:otherwise>
                            </c:choose>

                        </td>

                        <td class="text-muted">

                            <c:choose>
                                <c:when test="${order.state=='DONE'}">
                                    <%--<a href="<c:url value="/pay/${order.codeNumber}"/>">pay</a>--%>
                                    <a href="<c:url value="/customer/payWithCreditCard/${order.codeNumber}"/>"> credit</a>
                                    <br/>
                                    <a href="<c:url value="/customer/payOnline/${order.codeNumber}"/>">online</a>
                                </c:when>
                                <c:otherwise>
                                    pay
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>

        </div>
    </div>

</div>
</body>
</html>


