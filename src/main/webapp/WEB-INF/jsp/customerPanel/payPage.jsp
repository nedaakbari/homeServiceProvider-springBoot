<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!-- Created By CodeWithNepal -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title> payPage</title></title>
    <link rel="stylesheet" href="<c:url value="/static/css/customerProfile.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/payment.css"/>">

    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <script src="dist/jquery.payform.js"></script>
    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' rel='stylesheet'>
    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script type='text/javascript' src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script>
    <script type='text/javascript' src='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'></script>
    <script async src='/cdn-cgi/bm/cv/669835187/api.js'></script>
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
            <h1>Pay online </h1>

            <div id="ten-countdown"></div>
            <table class="table table-bordered table-striped text-info">
                <form:form  action="/customer/payWithOnline" method="post" modelAttribute="transAction">
                    <p class="text-danger" style="text-color:red !important;">${error}</p>
                    <tr>
                        <td>
                            <div >
                                <p>amount:${amount}</p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                <p>accNumber</p>
                                <form:input path="accNumber" type="tel" name="accNumber" maxlength="16"
                                            placeholder="accNumber"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                <p>expireDate</p>
                                <form:input type="date"  path="expireDate" name="expireDate" placeholder="expireDate"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="input-box">
                                <p>cvv2</p>
                                <form:input path="cvv2" type="tel" name="cvv2" placeholder="cvv2" maxlength="4"/>

                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td>

                            <button type="submit" class="btn  btn-lg btn-block text-white"
                                    name="/customer/payOnline " style="background-color:  #342148 !important;
                                         width: 100%;height: 100%">payOnline
                            </button>

                        </td>


                    </tr>

                </form:form>
            </table>
            <br/>

        </div>
    </div>

</div>



<script type="text/javascript">

    /*    for timeOut*/
    function countdown(elementName, minutes, seconds) {
        var element, endTime, hours, mins, msLeft, time;

        function twoDigits(n) {
            return (n <= 9 ? "0" + n : n);
        }

        function updateTimer() {
            msLeft = endTime - (+new Date);
            if (msLeft < 1000) {
                element.innerHTML = "Time is up!";
            } else {
                time = new Date(msLeft);
                hours = time.getUTCHours();
                mins = time.getUTCMinutes();
                element.innerHTML = (hours ? hours + ':' + twoDigits(mins) : mins) + ':' + twoDigits(time.getUTCSeconds());
                setTimeout(updateTimer, time.getUTCMilliseconds() + 500);
            }
        }

        element = document.getElementById(elementName);
        endTime = (+new Date) + 1000 * (60 * minutes + seconds) + 500;
        updateTimer();
    }

    countdown("ten-countdown", 10, 0);

    setTimeout(function () {
        window.location.href = '/customer/dashboard';
    }, 1000000);

</script>

</body>
</html>


<%--<table class="table table-bordered table-striped text-info">
    <form:form modelAttribute="transAction" action="/payOnline" method="post">
        <p class="text-danger" style="text-color:red !important;">${error}</p>
        <tr>
            <td>
                <div class="input-box">
                    <form:input path="accNumber" name="accNumber" maxlength="16"
                                placeholder="accNumber"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="input-box">
                    <form:input type="date" path="expireDate" name="expireDate"
                                placeholder="expireDate"/>

                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="input-box">
                    <form:input type="number" path="cvv2" name="cvv2"
                                placeholder="cvv2" maxlength="4"/>

                </div>
            </td>
        </tr>

        <tr>
            <td>

                <button type="submit" class="btn  btn-lg btn-block text-white"
                        name="/payOnline " style="background-color:  #342148 !important;
                                         width: 100%;height: 100%">payOnline
                </button>

            </td>


        </tr>

    </form:form>
</table>--%>


