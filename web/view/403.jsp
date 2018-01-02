<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 02-Jan-18
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>403 - Access Denied</title>
</head>
<body>
<section style="text-align:center">
    <h1 style="font-size:340%">403</h1>
    <h1 style="font-size:340%">ACCESS DENIED!</h1>
    <h4><%= session.getAttribute("userId") %> don't have permission to access this page</h4>
    <%--<h5>Requested URI: <%= request.getAttribute("requestedPage") %></h5>--%>
    <a href="/"><h3>Back to Index Page</h3></a>
</section>
</body>
</html>
