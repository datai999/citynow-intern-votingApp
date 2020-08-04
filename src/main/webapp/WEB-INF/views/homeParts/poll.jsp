<%@ page import="model.dto.poll.PollBuilder" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/4/2020
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    Poll currentPoll;
    UserAccount pollCreator;

    boolean voted;
    int votedOptionId;
%>

<%
    currentPoll = (Poll) request.getAttribute("currentPoll");
    if (currentPoll == null) {
        currentPoll = new PollBuilder().buildBase(0, 0, null, null, null).build();
        pollCreator = new UserAccount(null,null,null,"null");
        voted = true;
    }
    else pollCreator = currentPoll.getCreator();

    try{
        voted = (boolean) request.getAttribute("voted");
        votedOptionId = (int) request.getAttribute("votedOptionId");
    }catch (Exception e){
        voted = false;
        votedOptionId = 0;
    }

%>

<html>
<body>
<div class="card card-body">
    <form method="post" action="/home">
        <button type="submit" name="previous">Previous</button>
        <button type="submit" name="next">Next</button>
    </form>

    <br>


    <h2>Vote create by: <%=pollCreator.getFullName()%></h2>

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
</body>
</html>
