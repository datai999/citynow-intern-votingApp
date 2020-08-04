<%@ page import="model.dto.user.UserAccount" %>
<%@ page import="model.dto.user.UserRole" %>
<%@ page import="model.dto.poll.Poll" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.comment.CommentPoll" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    UserAccount user;

    String getTime(long timeStamp){

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        return df1.format(timeStamp*1000) + "T" + df2.format(timeStamp*1000);
    }
%>

<%
    user = (UserAccount) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div>

    <nav class="navbar navbar-light bg-light justify-content-between">

        <button type="button" class="btn btn-primary" onclick="location.href ='/home'">App</button>

        <h3>Home page: <%=user==null?"GUEST":user.getUsername()%></h3>

        <div>
            <% if (user != null && user.getRole() != UserRole.CUSTOMER.value)  {%>
            <button type="button" class="btn btn-primary" onclick="location.href ='/create'">Create</button>
            <%  } %>

            <% if (user != null){ %>
            <button type="button" class="btn btn-primary" onclick="location.href ='/logout'">Logout</button>
            <% } else { %>
            <button type="button" class="btn btn-primary" onclick="location.href ='/login'">Login</button>
            <%}%>
        </div>

    </nav>

    <br>

    <div class="container-fluid">
        <div class="row">
            <div class=" col-sm-8 pl-5">
                <div class="row">
                    <%@ include file="homeParts/poll.jsp" %>
                </div>
                <div class="row">
                    <%@ include file="homeParts/commentView.jsp" %>
                </div>
                <div class="row">
                    <%@ include file="homeParts/comment.jsp" %>
                </div>
            </div>

            <div class=" col-sm-4">
                <%@ include file="homeParts/topVote.jsp" %>
            </div>
        </div>

    </div>
</div>



</body>
</html>
