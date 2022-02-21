<%@ page import="ir.maktab.dto.ExpertDto" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!-- Created By CodeWithNepal -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>OfferForm</title>
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
                    <li><a href="/expert/dashboard"><i class="fas fa-user"></i>Dashboard</a></li>
                    <li><a href="/expert/categoryList"><i class="fas fa-sliders-h"></i>CategoryList</a></li>
                    <li><a href="/expert/showAllOrders"><i class="fas fa-address-book"></i>Offer</a></li>
                    <li><a href="/expert/showAllOffersOfExpert"><i class="fas fa-cog"></i>Offers History</a></li>
                    <li><a href="/expert/showAllOffersOfExpertWithStatus"><i class="fas fa-cog"></i>accepted Offers</a></li>
                    <li><a href="/expert/showSpeciality"><i class="fas fa-stream"></i>see your speciality</a></li>
                </ul>
            </nav>
        </div>

    </div>
    <div class="cole-6">
        <div class=" text-center" style="padding : 60px 400px 0px 400px">
            <h3>Place offer </h3>
            <p class="text-danger" style="text-color:red !important;">${error}</p>
            <table class="table table-bordered table-striped text-info">
                <form:form modelAttribute="offer" action="/expert/placeOffer" method="post">
                    <tr>
                        <td>
                            <div class="input-box row">
                                <div class="col-6 ">
                                    <p>Time for starting Work :</p>
                                </div>
                                <div class="col-6">
                                    <%--<form:input type="time" path="startWorkTime" name="startWorkTime"
                                                placeholder="startWorkTime"/>--%>
                                    <form:input type="text" path="startWorkTime" name="startWorkTime"
                                                placeholder="startWorkTime"/>
                                        <br/>
                                    <form:errors path="startWorkTime" cssClass="text-danger"/>
                                </div>

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                <form:input type="number" path="duringTime" name="duringTime"
                                            step="0.01" placeholder="duringTime"/>
                                <form:errors path="duringTime" cssClass="text-danger"/>
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

                    <tr>
                        <td>
                            <div class="input-box">
                                <form:input type="number" step="0.01" path="proposedPrice" name="proposedPrice"
                                            placeholder="Enter your proposedPrice"/>
                                <form:errors path="proposedPrice" cssClass="text-danger"/>
                            </div>
                        </td>
                    </tr>
                        <td>
                           <%-- <%ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");%>
                            <c:if test="${sessionScope.expertDto.offer ne null}">
                                <button type="submit" class="btn text-white btn-lg btn-block" name="/placeOrder "
                                        style="background-color: rgb(52, 33, 72) !important;" >offer</button>
                            </c:if>--%>
                            <%--   <c:choose>
                                   <c:when test="${param.enter=='1'}">
                                       pizza.
                                       <br />
                                   </c:when>
                                   <c:otherwise>
                                       pizzas.
                                       <br />
                                   </c:otherwise>
                               </c:choose>--%>
                            <button type="submit" class="btn text-white btn-lg btn-block" name="/placeOrder "
                                    style="background-color: rgb(52, 33, 72) !important;" >offer</button>
                        </td>

                    </tr>

                </form:form>
            </table>
            <br/>

        </div>
    </div>

</div>
</body>
</html>



