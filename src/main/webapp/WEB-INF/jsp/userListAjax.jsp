<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script>
        jQuery(document).ready(function ($) {

            $("#search-form").submit(function (event) {

                console.log("submitted");
                // Disble the search button
                enableSearchButton(false);

                // Prevent the form from submitting via the browser.
                event.preventDefault();

                searchGetViaAjax();

            });

        });

        function searchViaAjax() {

            var search = {}
            search["firstName"] = $("#firstName").val();
            search["lastName"] = $("#lastName").val();
            search["email"] = $("#email").val();
            search["role"] = $("#role").val();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: window.location.origin + "/rest/search-users",
                data: JSON.stringify(search),
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    display(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });

        }

        function searchGetViaAjax() {

            search["firstName"] = $("#firstName").val();
            search["lastName"] = $("#lastName").val();
            search["email"] = $("#email").val();
            search["role"] = $("#role").val();

            $.ajax({
                type: "GET",
                url: window.location.origin + "/rest/search-users/search?firstName=" + firstName + "&lastName=" + lastName+
                    "&email=" + email+ "&role=" + role,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    display(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });

        }

        function enableSearchButton(flag) {
            $("#btn-search").prop("disabled", flag);
        }

        function display(data) {
            console.log(document.referrer)
            var tableHeaderRowCount = 2;
            var table = document.getElementById('userTbl');
            var rowCount = table.rows.length;
            for (var i = tableHeaderRowCount; i < rowCount; i++) {
                table.deleteRow(tableHeaderRowCount);
            }
            var trHTML = '';
            $.each(data, function (i, item) {
                console.log(item);
                trHTML += '<tr><td>' + item.firstName + '</td><td>' + item.lastName + '</td><td>' + item.email + '</td></tr>'+ '</td><td>' + item.role;
            });
            $('#productTbl').append(trHTML);
        }
    </script>

</head>


<body>
<form id="search-form" class="m-5 p-5 text-center" style="width: 1200px">
    <table id="productTbl" class="table table-striped table-success table-hover">
        <tr>
            <td>
                <label for="firstName">firstName</label>
                <input id="firstName" name="minPrice" placeHolder="firstName"/>
            </td>
            <td>
                <label for="lastName">lastName</label><input id="lastName" name="lastName" placeHolder="lastName"/>
            </td>
            <td>
                <label for="email">email</label>
                <input id="email" name="email" placeHolder="email"/>
            </td>

            <td>
                <label for="role">Choose a Role:</label>

                <select name="role" id="role">
                    <option value="">All</option>
                    <option value="CUSTOMER">customer</option>
                    <option value="EXPERT">expert</option>
                </select>
            </td>
            <td>
                <button type="submit" id="bth-search">Search</button>
            </td>
        </tr>
        <tr>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
            <th>role</th>
        </tr>
    </table>
    <div id="feedback"></div>
</form>
</body>
</html>
