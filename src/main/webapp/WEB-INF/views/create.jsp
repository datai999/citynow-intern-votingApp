<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%!
    String getMinTime(){
        Long currentTimestamp = System.currentTimeMillis();
        Long minTime = currentTimestamp + 4*60*60*1000;

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        return df1.format(minTime) + "T" + df2.format(minTime);
    }
%>

<html>
<head>
    <title>Create page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

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

<div>

    <div class="container">
        <div class="card">
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/create" method="post">

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Title</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="title"
                                   placeholder="Enter title" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Question</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="question"
                                   placeholder="Enter question" required>
                        </div>
                    </div>

                    <% for (int i=1; i < 5; i ++) {  %>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Option <%=i%></label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="options"
                                   placeholder="Enter option <%=i%>" required>
                        </div>
                    </div>
                    <%}%>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Deadline (date and time):</label>
                        <div class="col-sm-7">
                            <input type="datetime-local" class="form-control" name="deadline"
                                   value="<%=getMinTime()%>"
                                   min="<%=getMinTime()%>"
                                   max="2025-07-24T00:00"
                                   required>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
