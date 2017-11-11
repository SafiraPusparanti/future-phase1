<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 08-Oct-17
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../assets/css/bootstrap.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/header.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/layout.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>

    <title>Admin Menu</title>
</head>
<body>
    <%@ include file="/view/header.jsp"%>

    <div class="container">
    <div class="row">
        <div class="col text-center">
            <a href="#">
                <img border="0" alt="items" src="../assets/img/items.png" width="250" height="250">
            </a>
        </div>
        <div class="col text-center">
            <a href="#">
                <img border="0" alt="users" src="../assets/img/users.png" width="250" height="250">
            </a>
        </div>
        <div class="col text-center"><a href="#">
            <img border="0" alt="ledger" src="../assets/img/ledger.png" width="250" height="250">
        </a></div>
    </div>
    </div>
</body>
</html>
