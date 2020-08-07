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
    <title>Register page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


</head>
<body>

<div>

    <nav class="navbar navbar-light bg-light justify-content-between">

        <button type="button" class="btn btn-primary" onclick="location.href ='/home'">App</button>
        <h3>Register page</h3>
        <button type="button" class="btn btn-primary" onclick="location.href ='/login'">Login</button>
    </nav>

    <br>

    <div class="container">
        <div class="card">
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/register" method="post" enctype="multipart/form-data">

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Full Name</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="fullName"
                                   placeholder="Enter full name" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="email"
                                   placeholder="Enter email" required>
                        </div>
                    </div>

                    <div class=" form-group row">
                        <label class="col-sm-2 col-form-label">User Name</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="username"
                                   placeholder="Enter user name" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" name="password"
                                   placeholder="Enter Password" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Avatar</label>
                        <div class="col-sm-7">
                            <input id = "input" type='file' onchange="readFile()" required>
                        </div>
                    </div>

                    <input id="subAva" type="hidden" name="avatar">

                    <button id="submit" disabled="true" type="submit" class="btn btn-primary" >Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    async function readFile() {
        var file = document.getElementById("input").files[0];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        const result = await new Promise((resolve, reject) => {
            reader.onload = function(event) {
                resolve(reader.result)
            }
        })
        document.getElementById("subAva").setAttribute("value", result);
        document.getElementById("submit").disabled = false;
    }



</script>


</body>
</html>
