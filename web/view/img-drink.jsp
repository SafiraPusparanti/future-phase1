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
    Connection con2 = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/POS",
            "postgres",
            "aldoleonardo");

    Statement state2 = con2.createStatement();
    String sql2 = "SELECT * FROM product WHERE category_id LIKE 'DR'";

    ResultSet rs2 = state2.executeQuery(sql2);
    while (rs2.next()){
        String name2 = rs2.getString(2);
        String img2 = rs2.getString(6);
        String price2 = String.valueOf(rs2.getInt(3));

        out.println("<img src =" + img2 + " data-name = \"" + name2 +
                "\" data-price = " + price2 +
                " width = 100 height = 100>");
    }
%>
</body>
</html>
