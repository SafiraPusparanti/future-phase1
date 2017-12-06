<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16/11/2017
  Time: 12.58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
</head>
<body>

<div class="container">


    <h1><font>LOGIN</font></h1>
    <form action="/login/cek" method="post">
        <input type="text" class="input" name="username" placeholder="Username"><br>
        <input type="password" class="input" name="password" placeholder="Password"> <br>
        <input type="submit" id="button" name="" value="Login">
        <%
            String username = request.getParameter("username");
            session.setAttribute("name", username);
        %>
    </form>


</div>

</body>
</html>
