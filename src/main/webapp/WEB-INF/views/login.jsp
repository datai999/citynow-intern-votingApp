<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

    <form method="post" action="/login">

        <div class="container">
            <label ><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>
            <br><br>
            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>
            <br><br>
            <button type="submit">Login</button>
        </div>

    </form>
</div>


</body>
</html>
