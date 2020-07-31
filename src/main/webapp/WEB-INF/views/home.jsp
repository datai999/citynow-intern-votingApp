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

    List<Poll> lsTopPoll;
    List<UserAccount> lsTopPollUser;


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


    lsTopPoll = (List<Poll>) request.getAttribute("lsTopPoll");
    lsTopPollUser = (List<UserAccount>) request.getAttribute("lsTopPollUser");
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


    <form method="post" action="/vote" >

        <input style="visibility: hidden" name="poll" value="<%=currentPoll.getId()%>">
        <br>
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
        <p><%=currentPoll.getNumBallot()%><label> Vote</label></p>
        <label>Deadline: <input type="datetime-local" disabled="disabled" value="<%=getTime(currentPoll.getTimeEnd())%>"></label>
        <br>
    </form>

</div>

<br>
<br>
<div>
    <form method="post" action="/comment" >

        <input style="visibility: hidden" name="pollId" value="<%=currentPoll.getId()%>">
        <br>
        <input type="text" placeholder="Enter your comment" name="content">

    </form>
</div>

<h2>Top poll</h2>
<table>
    <tr>
        <th>No</th>
        <th>Tittle</th>
        <th>Create by</th>
        <th>Start</th>
        <th>End</th>
        <th>Ballot</th>
    </tr>

    <% for (int i =0; i < lsTopPoll.size(); i++) { Poll poll = lsTopPoll.get(i); UserAccount user = lsTopPollUser.get(i); %>
    <tr >
        <th><%=i+1%></th>
        <th><%=poll.getTitle()%></th>
        <th><%=user.getFullName()%></th>
        <th><%=poll.getTimeStart()%></th>
        <th><%=poll.getTimeEnd()%></th>
        <th><%=poll.getNumBallot()%></th>
    </tr>
    <% } %>

</table>



</body>
</html>
