<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06/12/2017
  Time: 13.28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@page import="java.io.File" %>
<%@ page import="java.sql.*" %>

<%
    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/POS",
            "postgres",
            "aldoleonardo");

    Statement state = con.createStatement();
    String sql = "SELECT image_url FROM product WHERE category_id LIKE 'DR'";

    ResultSet rs = state.executeQuery(sql);
    while (rs.next()){
        String img = rs.getString(1);
        out.println("<img src =" + img + " width = 100 height = 100 >");
    }
%>
</body>
</html>
