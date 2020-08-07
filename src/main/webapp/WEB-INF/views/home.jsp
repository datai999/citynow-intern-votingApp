<%@ page import="model.dtO.user.UserAccount" %>
<%@ page import="model.dtO.user.UserRole" %>
<%@ page import="model.dtO.poll.Poll" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dtO.comment.CommentPoll" %><%--
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
<body onload="load()">

<div>

    <nav class="navbar navbar-light bg-light justify-content-between">

        <button type="button" class="btn btn-primary" onclick="location.href ='/home'">App</button>

        <h3>Home page: <%=user==null?"GUEST":user.getUsername()%></h3>

        <div>

            <% if (user != null && user.getRole() == UserRole.ROOT.value)  {%>
            <button type="button" class="btn btn-primary" onclick="location.href ='/root'">Manager</button>
            <%  } %>

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
            <div class=" col-sm-8 pl-5" style="height: 90vh">
                <%for (Poll poll: lsPoll){ currentPoll = poll;%>
                <div class="poll">
                    <div class="row" style="height: 45vh">
                        <%@ include file="homeParts/poll.jsp" %>
                    </div>
                    <div class="row" style="height: 35vh">
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
<%--                    <%@ include file="homeParts/topCmt.jsp" %>--%>
                </div>
            </div>
        </div>

    </div>
</div>

<script>

    let polls = document.getElementsByClassName("poll");
    let pollIds = document.getElementsByName("poll");
    let poll = document.getElementById("pollIdCmt");
    let pollIndex = polls.length - 1;
    let cmtViewIndex = 0;

    function nextPoll(n) {
        showPoll(pollIndex += n);
        cmtViewIndex = 0;
        showCmtView(cmtViewIndex, pollIds[pollIndex].value);
    }

    function showPoll(n) {
        if (n === polls.length) {pollIndex = 0}
        if (n < 0) {pollIndex = polls.length-1}
        for (let i = 0; i < polls.length; i++) {
            polls[i].style.display = "none";
        }
        polls[pollIndex].style.display = "block";
        poll.value = pollIds[pollIndex].value;
    }



    function nextCmtView(n, pollId) {
        showCmtView(cmtViewIndex += n, pollId);
    }

    function showCmtView(n, pollId) {
        let cmtViews = document.getElementsByName("commentView"+ pollId);

        if (n === cmtViews.length) {cmtViewIndex--; return;}
        if (n < 0) {cmtViewIndex = 0; return;}
        for (let i = 0; i < cmtViews.length; i++) {
            cmtViews[i].style.display = "none";
        }
        cmtViews[cmtViewIndex].style.display = "block";
    }

    function load() {
        showPoll(pollIndex);
        showCmtView(cmtViewIndex, <%=lsPoll.get(lsPoll.size()-1).getId()%>);
    }

</script>

</body>
</html>
