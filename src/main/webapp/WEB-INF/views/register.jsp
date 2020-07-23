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
    <h3>Register page</h3>
    <button type="button" onclick="location.href ='/login'">Login</button>

</div>

<br>

<div>

    <form method="post" action="/register">

        <div class="container">
            <label ><b>full name</b></label>
            <input type="text" placeholder="Enter your full name" name="fullName" required>
            <br><br>
            <label ><b>username</b></label>
            <input type="text" placeholder="Enter your user name" name="username" required>
            <br><br>
            <label ><b>email</b></label>
            <input type="text" placeholder="Enter your email" name="email" required>
            <br><br>
            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>
            <br><br>
            <button type="submit">submit</button>
        </div>

    </form>
</div>


</body>
</html>
