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

    String getTime(long timeStamp){

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        return df1.format(timeStamp*1000) + "T" + df2.format(timeStamp*1000);
    }
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

    <div class="row">
        <div class="col-sm-10">
            <h3>Creator: <%=pollCreator.getFullName()%></h3>

            <div class="row">
                <div class="col">
                    <label><b>Start: </b><%=time2String(currentPoll.getTimeStart())%></label>
                </div>
                <div class="col">
                    <label><b>End: </b><%=time2String(currentPoll.getTimeEnd())%></label>
                </div>
                <div class="col">
                    <label><b>Voted: </b> <%=currentPoll.getNumBallot()%></label>
                </div>
            </div>

        </div>
        <div class="col-sm">
            <form method="post" action="/home">
                <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary mr-3" name="previous">Previous</button>
                        <button type="submit" class="btn btn-primary" name="next">Next</button>
                </div>
            </form>
        </div>
    </div>

    <br>

    <h3>Title: <%=currentPoll.getTitle()%></h3>
    <h4><%=currentPoll.getQuestion()%></h4>

    <form method="post" action="/vote" >

        <input type="hidden" name="poll" value="<%=currentPoll.getId()%>">

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
<%--        <br>--%>
        <button type="submit" class="btn btn-primary"
                <%if (voted) {%>
                disabled="disabled"
                <%}%>
        >
            Vote
        </button>

    </form>
</div>
</body>
</html>
