<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/4/2020
  Time: 9:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    List<Poll> lsTopPoll;
%>

<%
    lsTopPoll = (List<Poll>) request.getAttribute("lsTopPoll");
    if (lsTopPoll == null) lsTopPoll = new ArrayList<>();
%>

<html>
<body>
<div class="card card-body">
    <h4>Top poll has many vote</h4>


    <% for (int i =0; i < lsTopPoll.size(); i++) {
        Poll poll = lsTopPoll.get(i);
        UserAccount user = poll.getCreator();
        if (user == null) user = new UserAccount(null,null,null,"null");
    %>

    <div class="media mb-1" style="border: 0.5px solid; border-radius: 5px;">
        <img class="media-object" src="https://upload.wikimedia.org/wikipedia/vi/b/b0/Avatar-Teaser-Poster.jpg" width="64" height="64" alt="...">

        <div class="media-body pl-2">

            <div class="row">
                <div class="col-sm-8">
                    <h5><%=poll.getTitle()%></h5>
                    <h6 class="media-heading"><%=user.getFullName()%></h6>
                </div>
                <div class="col-sm-2 p-0" style="font-size: 0.8em">
                    <p class="my-1"><%=time2String(poll.getTimeStart())%></p>
                    <p class="my-1"><%=time2String(poll.getTimeEnd())%></p>
                </div>
                <div class="col-sm-2 p-0 d-flex justify-content-center">
                    <h5 class="mt-3"><%=poll.getNumBallot()%></h5>
                </div>
            </div>


        </div>

    </div>

    <% } %>


</div>
</body>
</html>
