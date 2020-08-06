<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 8/4/2020
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<body>
<div class="card card-body p-1">
    <form method="post" action="/comment" >
        <input id = "pollIdCmt" type="hidden" name="pollId">
        <div class="d-flex">
            <img class="media-object" src="<%=user==null?url_avatar_default:user.getUrlAvatar()%>" width="48" height="48" alt="...">
            <input class="form-control mr-sm-1" type="text" placeholder="Enter your comment" name="content">
            <button class="btn btn-primary" type="submit">Comment</button>
        </div>
    </form>
</div>
</body>
</html>
