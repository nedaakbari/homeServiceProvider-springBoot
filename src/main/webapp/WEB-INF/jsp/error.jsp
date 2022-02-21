
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <link rel="stylesheet" href="<c:url value="/static/css/adminPanel.css"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Access Denied</title>
        <style>

        </style>
    </head>
<body>
<p>error page</p>

</body>

</html>



<%--
<form:form cssClass="m-5 p-5 text-center" cssStyle="width: 1200px" modelAttribute="userFilterDto" action="/user/search"
           method="post">
    <table class="table table-striped table-success table-hover">
        <tr>
            <td>
                <form:input path="firstName" name="firstName" placeHolder="First Name"/>
            </td>
            <td>
                <form:input path="lastName" name="lastName" placeHolder="Last Name"/>
            </td>
            <td>
                <form:input path="role" name="role" placeHolder="Role"/>
            </td>
            <td>
                <form:input path="email" name="email" placeHolder="Email"/>
            </td>
            <td>
                <form:button name="search">Search</form:button>
            </td>
        </tr>
        <tr>
            <th>Last Name</th> &lt;%&ndash;هدرهاش هستن اینا&ndash;%&gt;
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.lastName}</td>
                <td>${user.firstName}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
    </table>
</form:form>
</body>
</html>

--%>

