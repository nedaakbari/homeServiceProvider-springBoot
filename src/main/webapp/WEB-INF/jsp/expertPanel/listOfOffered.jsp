

 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

 <!DOCTYPE html>
 <!-- Created By CodeWithNepal -->
 <html lang="en" dir="ltr">
 <head>
     <meta charset="utf-8">
     <title>ListOfOffered</title>
     <link rel="stylesheet" href="<c:url value="/css/customerProfile.css"/>">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
           integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

 </head>
 <body>

 <div>

     <div class=" p-3 px-md-4 d-flex flex-column align-items-center text-color-white   border-bottom shadow-sm p-3  flex-md-row"
          style="background-color :antiquewhite">
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
                     <li><a href="/expertDashboard"><i class="fas fa-user"></i>Dashbord</a></li>
                     <li><a href="/expertCategoryList"><i class="fas fa-sliders-h"></i>CategoryList</a></li>
                     <li><a href="/showAllOrders"><i class="fas fa-address-book"></i>Offer</a></li>
                     <li><a href="/showAllOffersOfExpert"><i class="fas fa-cog"></i>Offers History</a></li>
                     <li><a href="/showAllOffersOfExpertWithStatus"><i class="fas fa-cog"></i>accepted Offers</a></li>
                     <li><a href="/showSpeciality"><i class="fas fa-stream"></i>see your speciality</a></li>
                     <li><a href="#"><i class="fas fa-globe-asia"></i>Languages</a></li>
                     <li><a href="#"><i class="fas fa-envelope"></i>Contact us</a></li>
                 </ul>
             </nav>
         </div>

     </div>
     <div class="cole-6">
         <div class=" text-center" style="padding : 60px 300px 0px 300px">
             <h1>Order List</h1>

             <table class="table table-bordered table-striped text-info">
                 <thead>
                 <tr>
                     <p class="text-danger" style="text-color:red !important;">${error}</p>
                     <th scope="col">SubService title</th>
                     <th scope="col">duringTime</th>
                     <th scope="col">startWorkTime</th>
                     <th scope="col">proposedPrice</th>
                     <th scope="col">description</th>
                     <th scope="col">status</th>
                     <th scope="col">submissionDate</th>
                 </tr>
                 </thead>
                 <tbody>

                 <c:forEach var="offer" items="${allOfferAnExpert}">
                     <tr>
                         <td>${offer.orders.subCategory.title}</td>
                         <td>${offer.duringTime}</td>
                         <td>${offer.startWorkTime}</td>
                         <td>${offer.proposedPrice}</td>
                         <td>${offer.description}</td>
                         <td>${offer.status}</td>
                         <td>${offer.submissionDate}</td>

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


