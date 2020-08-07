<%@ page import="model.dto.poll.PollBuilder" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/4/2020
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!

    UserAccount pollCreator;

    boolean voted = false;
    int votedOptionId;

    int typeNote = 0;
%>

<%

    voted = false;

    if (currentPoll == null) {
        currentPoll = new PollBuilder().buildBase(0, 0, null, null, null).build();
        pollCreator = new UserAccount(null,null,null,"null", "https://res.cloudinary.com/datai/image/upload/v1596599502/city_now/voting_app/avatar/defaul_avatar.png");
        voted = true;
    }
    else pollCreator = currentPoll.getCreator();

    if (user != null){
        votedOptionId = currentPoll.getVotedId();
        voted = votedOptionId>0;
        typeNote = voted? 1:0;
    }

    if (!voted) {
        voted = ((int) (System.currentTimeMillis()/1000)) > currentPoll.getTimeEnd();
        typeNote = voted? 2:0;
    }

    if (!voted && user !=null) {
        voted = currentPoll.getVoteRole().value  > user.getRole();
        typeNote = voted? 3:0;
    }
%>

<html>
<head>

</head>

<body>
<div class="card card-body">

    <div class="row">
        <div class="col-sm-1">
            <img class="media-object" src="<%=currentPoll.getCreator().getUrlAvatar()%>" width="80" height="80" alt="...">
        </div>
        <div class="col-sm-8">
            <h3><%=pollCreator.getFullName()%></h3>

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
            <div class="d-flex justify-content-end">
                <button class="btn btn-primary mr-3" onclick="nextPoll(-1)">Previous</button>
                <button class="btn btn-primary" onclick="nextPoll(1)">Next</button>
            </div>
        </div>
    </div>

    <br>

    <div class="row">

        <div class="col-sm-8">

            <h3 id = "title">Title: <%=currentPoll.getTitle()%></h3>
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

                <%
                    switch (typeNote){
                        default: break;
                        case 1: %> (You have already voted) <% break;
                        case 2: %> (The poll is out of date) <% break;
                        case 3: %> (The poll is only view for you) <% break;

                    }
                %>

            </form>


        </div>


        <div class="col-sm-4">

            <%if (voted){%>

            <p class="mb-1 mt-3">Option 1</p>
            <div style="width: 100%; background-color: #ddd;">
                <div style="text-align: right; color: white; background-color: #4CAF50;
                        width: <%=(float)currentPoll.getOption(0).getCount()/(currentPoll.getNumBallot()+0.00000000000001)*100%>%">
                    <%=currentPoll.getOption(0).getCount()%>
                </div>
            </div>

            <p class="mb-1 mt-3">Option 2</p>
            <div style="width: 100%; background-color: #ddd;">
                <div style="text-align: right; color: white; background-color: #2196F3;
                        width: <%=(float)currentPoll.getOption(1).getCount()/(currentPoll.getNumBallot()+0.00000000000001)*100%>%">
                    <%=currentPoll.getOption(1).getCount()%>
                </div>
            </div>

            <p class="mb-1 mt-3">Option 3</p>
            <div style="width: 100%; background-color: #ddd;">
                <div style="text-align: right; color: white; background-color: #f44336;
                        width: <%=(float)currentPoll.getOption(2).getCount()/(currentPoll.getNumBallot()+0.00000000000001)*100%>%">
                    <%=currentPoll.getOption(2).getCount()%>
                </div>
            </div>

            <p class="mb-1 mt-3">Option 4</p>
            <div style="width: 100%; background-color: #ddd;">
                <div style="text-align: right; color: white; background-color: #808080;
                        width: <%=(float)currentPoll.getOption(3).getCount()/(currentPoll.getNumBallot()+0.00000000000001)*100%>%">
                    <%=currentPoll.getOption(3).getCount()%>
                </div>
            </div>

            <%}%>

        </div>



    </div>

</div>
</body>
</html>
