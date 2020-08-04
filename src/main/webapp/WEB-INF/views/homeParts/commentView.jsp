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
        <img class="media-object" src="https://upload.wikimedia.org/wikipedia/vi/b/b0/Avatar-Teaser-Poster.jpg" width="48" height="48" alt="...">

        <div class="media-body pl-2">

            <div class="row">
                <div class="col-sm-10">
                    <h6><%=cmt.getCommentator().getFullName()%></h6>
                </div>
                <div class="col-sm-2 p-0" style="font-size: 0.8em">
                    <p class="my-1"><%=time2String(cmt.getTimeCreate())%></p>
                </div>
            </div>

            <p style="font-size: 0.8em"><%=cmt.getContent()%></p>


        </div>

    </div>

    <% } %>


</div>
</body>
</html>
