<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/4/2020
  Time: 9:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<body>
<div class="card card-body">

    <%for (int i =0; i < (currentPoll.getLsCmt().size()-1)/4 +1; i++) { %>
    <div name="commentView<%=currentPoll.getId()%>" style="height: 28vh">
        <%for (int j=0; j < 4; j++){
            if (i*4+j == currentPoll.getLsCmt().size()) break;
            CommentPoll cmt = currentPoll.getLsCmt().get(i*4+j); %>


        <div class="media mb-1" style="border: 0.5px solid; border-radius: 5px;">
            <img class="media-object" src="<%=cmt.getCommentator().getUrlAvatar()%>" width="48" height="48" alt="...">

            <div class="media-body pl-2">
                <div class="d-flex">
                    <h6 class="mr-auto"><%=cmt.getCommentator().getFullName()%></h6>
                    <p class="p-0 my-1 mr-1" style="font-size: 0.6em"><%=time2String(cmt.getTimeCreate())%></p>
                </div>
                <p style="font-size: 0.8em"><%=cmt.getContent()%></p>
            </div>

        </div>
        <%}%>
    </div>
    <% } %>

    <div class="mt-2" style="text-align:center;">
        <button class="btn btn-primary btn-sm" onclick="nextCmtView(-1, <%=currentPoll.getId()%>)">Previous</button>
        <label name="currentCmtView">1</label>
        <label>/</label>
        <label name="totalCmtView">1</label>
        <button class="btn btn-primary btn-sm" onclick="nextCmtView(1, <%=currentPoll.getId()%>)">Next</button>
    </div>

</div>

</body>
</html>
