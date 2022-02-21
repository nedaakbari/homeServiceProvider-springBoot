<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>homePage</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/> ">

</head>
<body>

<%-- header--%>
<div class=" p-3 px-md-4 d-flex flex-column align-items-center text-white   border-bottom shadow-sm p-3  flex-md-row"
     style="background-color :#8b177a">
    <div class="col row center">
        <div class="col row">
            <h5 class="my-0 mr-md-auto font-weight-bold text-white">HomeServiceProvider</h5>
        </div>
        <%--<div class="col row">
            <img src="<c:url value="/static/image/31.png"/>  " width="50">
        </div>--%>
    </div>

    <div class="col">

    </div>
    <div class="col">
        <h2></h2>
    </div>

    <nav class="my-2 my-md-0 mr-md-3 text-white">
        <a class="p-2 text-white " href="#">Features</a>
        <a class="p-2 text-white" href="#">Enterprise</a>
        <a class="p-2 text-white" href="#">Support</a>
        <a class="p-2 text-white" href="#">Pricing</a>
    </nav>
<%--<a class="btn btn-outline-light" href="customerPages/registers.jsp">Order</a>--%>
    <a href="<c:url value="/register"/>" class="btn btn-Light ">register</a>
    <%-- <a class="btn btn-bd-download d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3" href="https://github.com/twbs/bootstrap/archive/v4.0.0.zip">Download</a>--%>
 </div>
 <%-- header--%>

<div>
    <img src="<c:url value="/static/image/10.png"/> " width="100% " style="position: absolute">
    <div class="btn-group mt-3 mx-3">
        <a href="<c:url value="/login"/>" class="btn btn-outline-dark">Login</a>
    </div>
    <%--  <a class="btn btn-outline-success" href="expertRegister">Be Expert</a>--%>
</div>


<div class="container" style="margin-top: 550px">

    <div>

        <div class="row  text-center">
            <div class="col">
                <h1></h1>
            </div>
            <div class="col">
                <h3>Which Service Are U Looking For?</h3>
            </div>
            <div class="col">
                <h1></h1>
            </div>

        </div>

        <div class="row  text-center" style="margin-top: 20px">
            <div class="col">
                <img src="<c:url value="/static/image/31.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>
            </div>
            <div class="col">
                <img src="<c:url value="/static/image/30.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>
            </div>
            <div class="col">
                <img src="<c:url value="/static/image/32.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>

            </div>
            <div class="col">
                <img src="<c:url value="/static/image/33.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>

            </div>
        </div>
        <div class="row  text-center">
            <div class="col">
                <img src="<c:url value="/static/image/31.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>
            </div>
            <div class="col">
                <img src="<c:url value="/static/image/30.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>
            </div>
            <div class="col">
                <img src="<c:url value="/static/image/32.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>

            </div>
            <div class="col">
                <img src="<c:url value="/static/image/33.png"/> ">
                <h6 style="text-align: center">HOME-SERVICE</h6>

            </div>
        </div>

    </div>


</div>


<%--
<div class="container">
    <div class="pricing  pt-3 pb-2 text-center">
        <h1 class="display-4"></h1>
        <p class="lead"></p>
    </div>
</div>
--%>

<div class="container text-center" style="margin-top: 100px ">
    <div class=" row   "  style="padding-left: 30px" >
        <div class="col">
            <div class="card ">
                <div class="card-header ">
                    <h4> Free</h4>
                </div>
                <div class="card-body">
                    <h3 class="card-title">$0 <small class="text-muted">/ mo</small></h3>
                    <p class="card-text">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>10 users included</li>
                        <li>2 GB of storage</li>
                        <li>Email support</li>
                        <li>Help center access</li>
                    </ul>
                    </p>
                    <button type="button" class="btn btn-lg btn-block btn-outline-success">Sign up for free</button>
                    <!--  <a href="#" class="btn btn-primary">Sign up for free</a> ! -->

                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h4> Pro </h4>
                </div>
                <div class="card-body">
                    <h3 class="card-title">$15 <small class="text-muted">/ mo</small></h3>
                    <p class="card-text">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>20 users included</li>
                        <li>10 GB of storage</li>
                        <li>Priority email support</li>
                        <li>Help center access</li>
                    </ul>
                    </p>
                    <button type="button" class="btn btn-lg btn-block btn-success">Get started</button>
                    <!--  <a href="#" class="btn btn-primary">Get started</a>! -->
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    <h4> Enterprise </h4>
                </div>
                <div class="card-body">
                    <h3 class="card-title">$29 <small class="text-muted">/ mo</small></h3>
                    <p class="card-text">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>30 users included</li>
                        <li>15 GB of storage</li>
                        <li>Phone and email support</li>
                        <li>Help center access</li>
                    </ul>
                    </p>
                    <button type="button" class="btn btn-lg btn-block btn-success">Contact us</button>
                    <!--  <a href="#" class="btn btn-primary">Contact us</a>! -->
                </div>
            </div>
        </div>
    </div>

</div>


<!-- Footer -->
<footer class="page-footer font-small  pt-4 my-md-5 pt-md-5 border-top">
    <!-- Footer Links -->
    <div class="container text-center text-md-left">
        <!-- Grid row -->
        <div class="row">
            <!-- Grid column -->

            <div class="col-md-3 mx-auto">
                <!-- Links -->
                <h6 class="mt-3 mb-4 text-secondary">© 2017-2018</h6>
                <ul class="list-unstyled">
                </ul>
            </div>
            <!-- Grid column -->

            <hr class="clearfix w-100 d-md-none">

            <!-- Grid column -->
            <div class="col-md-3 mx-auto">

                <!-- Links -->
                <h5 class="font-weight-bold mt-3 mb-4">Features</h5>

                <ul class="list-unstyled ">
                    <li><a class="text-muted" href="#!">Cool stuff</a></li>
                    <li><a class="text-muted" href="#!">Random feature</a></li>
                    <li><a class="text-muted" href="#!">Team feature</a></li>
                    <li><a class="text-muted" href="#!">Stuff for developers</a></li>
                    <li><a class="text-muted" href="#!">Another one</a></li>
                    <li><a class="text-muted" href="#!">Last time</a></li>
                </ul>

            </div>
            <!-- Grid column -->

            <hr class="clearfix w-100 d-md-none">

            <!-- Grid column -->
            <div class="col-md-3 mx-auto">

                <!-- Links -->
                <h5 class="font-weight-bold mt-3 mb-4">Resources</h5>

                <ul class="list-unstyled">

                    <li><a class="text-muted" href="#!">Resource</a></li>
                    <li><a class="text-muted" href="#!">Resource name</a></li>
                    <li><a class="text-muted" href="#!">Another resource</a></li>
                    <li><a class="text-muted" href="#!">Final resource</a></li>

                </ul>

            </div>
            <!-- Grid column -->

            <hr class="clearfix w-100 d-md-none">

            <!-- Grid column -->
            <div class="col-md-3 mx-auto">

                <!-- Links -->
                <h5 class="font-weight-bold mt-3 mb-4">About</h5>

                <ul class="list-unstyled">
                    <li><a class="text-muted" href="#!">Team</a></li>
                    <li><a class="text-muted" href="#!">Locations</a></li>
                    <li><a class="text-muted" href="#!">Privacy</a></li>
                    <li><a class="text-muted" href="#!">Terms</a></li>

                </ul>

            </div>
            <!-- Grid column -->
        </div>
        <!-- Grid row -->
    </div>
</footer>


<%--<a href="customerform">Add Customer</a>--%>
<%--<a href="viewemp">Be Expert</a>--%>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
</body>
</html>


