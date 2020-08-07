<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.dto.user.UserAccount" %>
<%@ page import="model.dto.user.UserRole" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%!
    String getMinTime(int minute){
        Long currentTimestamp = System.currentTimeMillis();
        Long minTime = currentTimestamp + minute*60*1000;

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        return df1.format(minTime) + "T" + df2.format(minTime);
    }
%>

<html>
<head>
    <title>Create question page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>


<div>

    <nav class="navbar navbar-light bg-light justify-content-between">

        <button type="button" class="btn btn-primary" onclick="location.href ='/home'">App</button>
        <h3>Create question page</h3>
        <div>
            <button type="button" class="btn btn-primary" onclick="location.href ='/home'">Back</button>
            <button type="button" class="btn btn-primary" onclick="location.href ='/logout'">Logout</button>
        </div>
    </nav>

    <br>

    <div class="container">
        <div class="card">
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/create" method="post">

                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Title</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title"
                                   placeholder="Enter title" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Question</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="question"
                                   placeholder="Enter question" required>
                        </div>
                    </div>

                    <% for (int i=1; i < 5; i ++) {  %>
                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Option <%=i%></label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="options"
                                   placeholder="Enter option <%=i%>" required>
                        </div>
                    </div>
                    <%}%>

                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Who can view</label>
                        <div class="col-sm-6 d-flex justify-content-between">
                            <label>
                                <input type="radio"  name="viewRole" checked="true" value="<%=UserRole.GUEST.value%>">
                                Everyone
                            </label>
                            <label>
                                <input type="radio"  name="viewRole" value="<%=UserRole.CUSTOMER.value%>">
                                User in system
                            </label>
                            <label>
                                <input type="radio"  name="viewRole" value="<%=UserRole.ADMIN.value%>">
                                Only admin
                            </label>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Who can vote</label>
                        <div class="col-sm-6 d-flex justify-content-between">
                            <div></div>
                            <label>
                                <input type="radio"  name="voteRole" checked="true" value="<%=UserRole.CUSTOMER.value%>">
                                User in system
                            </label>
                            <label>
                                <input type="radio"  name="voteRole" value="<%=UserRole.ADMIN.value%>">
                                Only Admin
                            </label>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Start (date and time):</label>
                        <div class="col-sm-6">
                            <input type="datetime-local" class="form-control" name="startTime"
                                   value="<%=getMinTime(0)%>"
                                   min="<%=getMinTime(0)%>"
                                   max="2025-07-24T00:00"
                                   required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-3 col-form-label">Deadline (date and time):</label>
                        <div class="col-sm-6">
                            <input type="datetime-local" class="form-control" name="deadline"
                                   value="<%=getMinTime(15)%>"
                                   min="<%=getMinTime(15)%>"
                                   max="2025-07-24T00:00"
                                   required>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
