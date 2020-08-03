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
    <title>Manager page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container">
    <button type="button" onclick="location.href ='/home'">App</button>
    <h3>Manager page</h3>
</div>
<br>


<div>

    <form method="post" action="/root" class="m-3">
        <button class="btn btn-primary" type="submit">Update</button>
        <br>
        <br>
        <table  class="table table-striped table-bordered table-hover">
            <thead>
            <tr >
                <th scope="col">No</th>
                <th scope="col">Id</th>
                <th scope="col">Full name</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Admin</th>
            </tr>
            </thead>

            <tbody>

            <% for (int i=0; i < lsUser.size(); i++) {
                UserAccount user = lsUser.get(i);  %>
            <tr >
                <th scope="row"><%=i+1%></th>
                <td><%=user.getId()%></td>
                <td><%=user.getFullName()%></td>
                <td><%=user.getUsername()%></td>
                <td><%=user.getEmail()%></td>
                <td>
                    <input type="checkbox" name="checkboxes" value=<%=user.getId()%>
                        <%if (user.getRole() != UserRole.CUSTOMER.value) {%>
                            checked="checked"
                        <%}%>
                        <%if (user.getRole() == UserRole.ROOT.value) {%>
                           disabled="disabled"
                        <%}%>
                    >
                </td>
            </tr>
            <% } %>

            </tbody>


        </table>

    </form>
</div>


</body>
</html>
