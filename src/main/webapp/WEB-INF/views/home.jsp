<%@ page import="model.dto.user.UserAccount" %>
<%@ page import="model.dto.user.UserRole" %>
<%@ page import="model.dto.poll.Poll" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.dto.poll.PollBuilder" %><%--
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


<%!
    UserAccount user;
    Poll currentPoll;
    UserAccount currentPollUser;
    boolean voted;
    int votedOptionId;

    int getPollVote(){
        int count = 0;
        for (int i=0; i<4; i++){
            count += currentPoll.getOption(i).getCount();
        }
        return count;
    }

    String getTime(long timeStamp){

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        return df1.format(timeStamp*1000) + "T" + df2.format(timeStamp*1000);
    }
%>

<%
    user = (UserAccount) request.getAttribute("user");

    try{
        voted = (boolean) request.getAttribute("voted");
        votedOptionId = (int) request.getAttribute("votedOptionId");
    }catch (Exception e){
        voted = false;
        votedOptionId = 0;
    }


    currentPoll = (Poll) request.getAttribute("currentPoll");
    currentPollUser = (UserAccount) request.getAttribute("currentPollUser");
    if (currentPoll == null) currentPoll = new PollBuilder().buildBase(0, 0, null, null, null).build();
    if (currentPollUser == null) {
        currentPollUser = new UserAccount(null,null,null,"null");
        voted = true;
    }


%>



<div class="container">
    <button type="button" onclick="location.href ='/home'">App</button>
    <h3>Home page: <%=user==null?"GUEST":user.getUsername()%></h3>

    <% if (user != null && user.getRole() != UserRole.CUSTOMER.value)  {%>
        <button type="button" onclick="location.href ='/create'">Create</button>
        <br><br>
    <%  } %>

    <% if(user!=null){ %>
        <button type="button" onclick="location.href ='/logout'">Logout</button>
    <% } else { %>
        <button type="button" onclick="location.href ='/login'">Login</button>
    <%}%>
</div>

<br>

<br><br>
<div class="container">

    <form method="post" action="/home">
        <button type="submit" name="previous">Previous</button>
        <button type="submit" name="next">Next</button>
    </form>

    <br>


    <h2>Vote create by: <%=currentPollUser.getFullName()%></h2>

    <label>Begin: <input type="datetime-local" disabled="disabled" value="<%=getTime(currentPoll.getTimeStart())%>"></label>
    <br>

    <h2><%=currentPoll.getTitle()%></h2>
    <p><%=currentPoll.getQuestion()%></p>
    <br>

    <form method="post" action="/vote">

        <% for (int i=0; i < 4; i ++) {  %>

            <label ><b>Option <%=i+1%>:</b></label>
            <label>
                <input type="radio"  name="options"
                    <%if (currentPoll.getOption(i).getId() == votedOptionId) {%>
                       checked="checked"
                    <%}%>
                    <%if (voted) {%>
                       disabled="disabled"
                    <%}%>
                       value="<%=currentPoll.getOption(i).getId()%>">
                 <%=currentPoll.getOption(i).getContent()%>
            </label>
            <br>

        <%}%>
        <br>
        <button type="submit"
                <%if (voted) {%>
                disabled="disabled"
                <%}%>
        >
            Vote
        </button>

        <br>
        <br>
        <p><%=getPollVote()%><label> Vote</label></p>
        <label>Deadline: <input type="datetime-local" disabled="disabled" value="<%=getTime(currentPoll.getTimeEnd())%>"></label>
        <br>
    </form>


</div>



</body>
</html>
