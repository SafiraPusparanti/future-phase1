<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06/12/2017
  Time: 13.27
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
    Connection con3 = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/POS",
            "postgres",
            "aldoleonardo");

    Statement state3 = con3.createStatement();
    String sql3 = "SELECT * FROM product WHERE category_id LIKE 'SN'";

    ResultSet rs3 = state3.executeQuery(sql3);
    while (rs3.next()){
        String name3 = rs3.getString(2);
        String img3 = rs3.getString(6);
        String price3 = String.valueOf(rs3.getInt(3));

        out.println("<img src =" + img3 + " data-name = \"" + name3 +
                "\" data-price = " + price3 +
                " width = 100 height = 100 >");
    }
%>
</body>
</html>
