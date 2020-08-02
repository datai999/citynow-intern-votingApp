<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>


<div class="container">
    <button type="button" onclick="location.href ='/home'">App</button>
    <h3>Login page</h3>
    <button type="button" onclick="location.href ='/register'">Register</button>
    <br>
</div>

<br>

<div>

    <div class="container">
        <div class="card">
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/login" method="post">

                    <div class=" form-group row">
                        <label class="col-sm-2 col-form-label">User
                            Name</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="username"
                                   placeholder="Enter user name" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Passwrod</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" name="password"
                                   placeholder="Enter Password" required>
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
