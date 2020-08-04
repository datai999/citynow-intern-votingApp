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
    <h2>Comment</h2>
    <table>
        <tr>
            <th>Comment by user</th>
            <th>Time comment</th>
            <th>Content</th>
        </tr>

        <% for (int i =0; i < lsComment.size(); i++) { CommentPoll cmt = lsComment.get(i); %>
        <tr >
            <th><%=cmt.getCommentator().getFullName()%></th>
            <th><%=cmt.getTimeCreate()%></th>
            <th><%=cmt.getContent()%></th>
        </tr>
        <% } %>

    </table>
</div>
</body>
</html>
