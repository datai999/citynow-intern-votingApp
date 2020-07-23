<%@ page import="model.DTO.UserAccount" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>

<% UserAccount user = (UserAccount) request.getAttribute("user"); %>

<div class="container">
    <button type="button" onclick="location.href ='/home'">App</button>
    <h3>Home page: <%=user.getFullName()%></h3>

        <% if (user.getRole() != UserAccount.CUSTOMER)  {%>
    <button type="button" onclick="location.href ='/create'">Create</button>
        <%  } %>
    <br>
    <button type="button" onclick="location.href ='/logout'">Logout</button>
</div>

<br>

</body>
</html>
