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
    String url_avatar_default = "https://res.cloudinary.com/datai/image/upload/v1596601506/city_now/voting_app/avatar/avatar_defaul.png";

    UserAccount user;

    String time2String(long timeStamp){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd-HH:mm");
        return df.format(timeStamp*1000);
    }
    Poll currentPoll;
    List<Poll> lsPoll;
%>

<%
    user = (UserAccount) request.getAttribute("user");

    lsPoll = (List<Poll>) request.getAttribute("lsPoll");
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

                <%for (Poll poll: lsPoll){ currentPoll = poll;%>
                <div class="poll">
                <div class="row">
                    <%@ include file="homeParts/poll.jsp" %>
                </div>
                <div class="row">
                    <%@ include file="homeParts/commentView.jsp" %>
                </div>
                </div>
                <%}%>

                <div class="row">
                    <%@ include file="homeParts/comment.jsp" %>
                </div>
            </div>

            <div class=" col-sm-4">
                <div class="card card-body">
                    <h2>Top last 3 days</h2>
                    <%@ include file="homeParts/topVote.jsp" %>
                    <%@ include file="homeParts/topCmt.jsp" %>
                </div>
            </div>
        </div>

    </div>
</div>

<script>

    let polls = document.getElementsByClassName("poll");
    var pollIndex = polls.length-1;
    showPoll(pollIndex);

    function nextPoll(n) {
        showPoll(pollIndex += n);
    }

    function showPoll(n) {
        if (n === polls.length) {pollIndex = 0}
        if (n < 0) {pollIndex = polls.length-1}
        for (let i = 0; i < polls.length; i++) {
            polls[i].style.display = "none";
        }
        polls[pollIndex].style.display = "block";
    }
</script>

</body>
</html>
