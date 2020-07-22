<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/20/2020
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>


<div class="container">
    <button type="button" class="cancelbtn">App</button>
</div>

<br>

<div class="container" >
    <button type="button" class="cancelbtn">Register</button>
</div>

<br>

<div>

    <form method="post" action="/login">

        <div class="container">
            <label ><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>

            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>
        </div>

    </form>
</div>


</body>
</html>
