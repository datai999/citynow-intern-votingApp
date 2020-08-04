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
<div class="card card-body">
    <div>
        <form method="post" action="/comment" >
            <input style="visibility: hidden" name="pollId" value="<%=currentPoll.getId()%>">
            <input type="text" placeholder="Enter your comment" name="content">
            <button class="btn btn-primary" type="submit">Comment</button>
        </form>
    </div>
</div>
</body>
</html>
