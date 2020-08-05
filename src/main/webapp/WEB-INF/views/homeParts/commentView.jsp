<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/4/2020
  Time: 9:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    List<CommentPoll> lsComment;


%>

<%
    lsComment = (List<CommentPoll>) request.getAttribute("lsComment");
    if (lsComment == null) lsComment = new ArrayList<>();
%>

<html>
<body>
<div class="card card-body">

    <% for (int i =0; i < lsComment.size(); i++) { CommentPoll cmt = lsComment.get(i); %>

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

    <% } %>


</div>
</body>
</html>
