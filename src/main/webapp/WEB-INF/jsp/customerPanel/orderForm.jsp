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
        <div class=" text-center" style="padding : 60px 400px 0px 400px">
            <h1>Place order </h1>

            <table class="table table-bordered table-striped text-info">
                <form:form modelAttribute="order" action="/customer/placeOrder" method="post">
                    <p class="text-danger" style="text-color:red !important;">${error}</p>
                    <tr>
                        <td>
                            <div class="input-box">
                                <form:input type="date" path="doWorkDate" name="doWorkDate"
                                            placeholder="Date you wanna workDone"/>
                                <form:errors path="doWorkDate" cssClass="text-danger"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                <form:input type="text" path="description" name="description"
                                            placeholder="description"/>
                                <form:errors path="description" cssClass="text-danger"/>
                            </div>
                        </td>

                    </tr>

                   <%-- <tr>
                        <td>
                            <div class="input-box">
                                <form:input type="number" step="0.01" path="proposedPrice" name="proposedPrice"
                                            placeholder="Enter your proposedPrice"/>
                                <form:errors path="proposedPrice" cssClass="text-danger"/>
                            </div>
                        </td>
                    </tr>--%>
                    <tr>
                        <td>
                            <div class="input-box">
                                    <%-- <form:input type="text" path="address.city" name="city"
                                                 placeholder="Enter your city"/>
                                     <form:errors path="address.city" cssClass="text-danger"/>--%>
                                <input type="text" name="city" placeholder="Enter your city"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                    <%-- <form:input type="text" path="address.zipCode" name="zipCode"
                                                 placeholder="Enter your zipCode"/>
                                     <form:errors path="address.zipCode" cssClass="text-danger"/>--%>
                                <input type="text" name="zipCode" placeholder="Enter your zipCode"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                    <%-- <form:input type="text" path="address.street" name="street"
                                                 placeholder="Enter your street"/>
                                     <form:errors path="address.street" cssClass="text-danger"/>--%>
                                <input type="text" name="street" placeholder="Enter your street"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                           <%-- <a  class="btn " type="submit"
                               name="/placeOrder " style="background-color: #983489 !important;
                                width: 100%;height: 100%">order</a>--%>
                                <button type="submit" class="btn  btn-lg btn-block text-white"
                                         name="/placeOrder "  style="background-color:  #342148 !important;
                                         width: 100%;height: 100%" >order</button>
                                <%--<button type="submit" name="/placeOrder"
                                        style="background-color: #5800bd !important;">order</button>--%>
                        </td>

                            <%--<div class="button input-box "
                                 style="background-color: #5800bd !important;">
                                <input type="submit" name="/placeOrder">
                            </div>--%>
                    </tr>

                </form:form>
            </table>
            <br/>

        </div>
    </div>

</div>
</body>
</html>



