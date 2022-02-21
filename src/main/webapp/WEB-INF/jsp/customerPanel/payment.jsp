<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>payment</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title>Snippet - GoSNippets</title>
    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="<c:url value="/static/css/payment.css"/>">

    <script src="//code.jquery.com/jquery-latest.min.js"></script>
    <script src="dist/jquery.payform.js"></script>


    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <script type='text/javascript' src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script>
    <script type='text/javascript' src='https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'></script>
    <script async src='/cdn-cgi/bm/cv/669835187/api.js'></script>
</head>
<body>
<%--<body oncontextmenu='return false' class='snippet-body'>--%>


<div id="ten-countdown"></div>
<div class="container mt-5 d-flex justify-content-center">
    <div class="card p-4">

<form:form modelAttribute="transAction"  action="/customer/payWithOnline" method="post">

        <div class="pt-4">
            <form:label path="accNumber" class="d-flex justify-content-between">
                <span class="label-text label-text-cc-number">CARD NUMBER</span>
                 </form:label>
           <%-- <label class="d-flex justify-content-between"> <span
                    class="label-text label-text-cc-number">CARD NUMBER</span>
            </label>--%>
            <form:input type="tel" path="accNumber" id="cardNumber"  name="credit-number" class="form-control credit-card-number"
                        maxlength="19" placeholder="•••• •••• •••• ••••"/>

        </div>

        <div class="d-flex justify-content-between pt-4">
           <%-- <div>
               &lt;%&ndash; <label>
                    <span class="label-text">EXPIRY</span>
                </label>&ndash;%&gt;
                <form:label path="expireDate" >
                    <span class="label-text">EXPIRY</span>
                </form:label>
                <form:input type="date"  path="expireDate" name="expiry-date" class="form-control expiry-class"/>
            </div>--%>
            <div>
             <%--   <label>
                    <span class="label-text">CVV</span>
                </label>--%>
                <form:label path="cvv2" >
                    <span class="label-text">CVV</span>
                </form:label>
                <form:input type="tel" path="cvv2" name="cvv-number" placeholder="••••"
                       class="form-control cvv-class" maxlength="4"
                       pattern="\d*"/>
            </div>
        </div>
        <div class="d-flex justify-content-between pt-5 align-items-center">
            <a href="<c:url value="/customer/orderList"/>" type="button" class="btn cancel-btn">Cancel</a>

            <%--<button type="button" class="btn cancel-btn">Cancel</button>--%>
            <form:button type="button" class="btn payment-btn" name="/payWithOnline">Make Payment</form:button>
        </div>
</form:form>
    </div>

</div>


<script type="text/javascript">

    /*    for separate 4 accnumber*/
    $('#cardNumber').on('keyup', function (e) {
        var val = $(this).val();
        var newval = '';
        val = val.replace(/\s/g, '');
        for (var i = 0; i < val.length; i++) {
            if (i % 4 == 0 && i > 0) newval = newval.concat(' ');
            newval = newval.concat(val[i]);
        }
        $(this).val(newval);
    });


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
        window.location.href = '/expertRegister';
    }, 1000000);

</script>

</body>
</html>













