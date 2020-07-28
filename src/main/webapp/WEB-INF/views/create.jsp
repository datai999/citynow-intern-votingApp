<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    String getMinTime(){
        Long currentTimestamp = System.currentTimeMillis();
        Long minTime = currentTimestamp + 5*60*1000;
        Date date = new Date(minTime);

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        return df1.format(minTime) + "T" + df2.format(minTime);
    }
%>

<html>
<head>
    <title>Create page</title>
</head>
<body>

<div class="container">
    <button type="button" onclick="location.href ='/home'">App</button>
    <h3>Create page</h3>
    <button type="button" onclick="location.href ='/home'">Back</button>
    <br><br>
    <button type="button" onclick="location.href ='/logout'">Logout</button>
</div>

<br><br><br><br>

<form method="post" action="/create">

    <div class="container">
        <label ><b>Title</b></label>
        <input type="text" placeholder="Enter title" name="title" required>
        <br><br>

        <label ><b>Question</b></label>
        <input type="text" placeholder="Enter question" name="question" required>
        <br><br>

        <% for (int i=1; i < 5; i ++) {  %>

        <label ><b>Option <%=i%></b></label>
        <input type="text" placeholder="Enter option <%=i%>" name="options" required>
        <br><br>

        <%}%>


        <label for="deadline">Deadline (date and time):</label>
        <input type="datetime-local" id="deadline" name="deadline"
               value="<%=getMinTime()%>"
               min="<%=getMinTime()%>"
               max="2025-07-24T00:00"

                required>

        <button type="submit">Submit</button>
    </div>

</form>

</body>
</html>
