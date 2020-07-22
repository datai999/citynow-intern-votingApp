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
<h3>register</h3>

<div>

    <form method="post" action="/register">

        <div class="container">
            <label ><b>full name</b></label>
            <input type="text" placeholder="Enter your full name" name="fullName" required>

            <label ><b>username</b></label>
            <input type="text" placeholder="Enter your user name" name="username" required>

            <label ><b>email</b></label>
            <input type="text" placeholder="Enter your email" name="email" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">submit</button>
        </div>

    </form>
</div>


</body>
</html>
