<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.dto.user.UserAccount" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.user.UserRole" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/23/2020
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<UserAccount> lsUser = (List<UserAccount>) request.getAttribute("lsUser"); %>

<%!
    public void get()  { }
%>



<html>
<head>
    <title>Title</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td,
        th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<div class="container">
    <button type="button" onclick="location.href ='/home'">App</button>
    <h3>Manager page</h3>
</div>
<br>

<form method="post" action="/root">
    <button type="submit">Update</button>
<table>
    <tr>
        <th>id</th>
        <th>Full name</th>
        <th>Username</th>
        <th>Email</th>
        <th>Admin</th>
    </tr>

<% for (UserAccount user:lsUser) {  %>
    <tr >
        <th><%=user.getId()%></th>
        <th><%=user.getFullName()%></th>
        <th><%=user.getUsername()%></th>
        <th><%=user.getEmail()%></th>
        <th>
            <input type="checkbox" name="checkboxes" value=<%=user.getId()%>
                <%if (user.getRole() != UserRole.CUSTOMER.value) {%>
                   checked="checked"
                <%}%>
                <%if (user.getRole() == UserRole.ROOT.value) {%>
                   disabled="disabled"
                <%}%>
            >
        </th>
    </tr>
<% } %>

</table>

</form>


</body>
</html>
