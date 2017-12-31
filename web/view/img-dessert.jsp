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
    Connection con1 = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/POS",
            "postgres",
            "aldoleonardo");

    Statement state1 = con1.createStatement();
    String sql1 = "SELECT * FROM product WHERE category_id LIKE 'DE'";

    ResultSet rs1 = state1.executeQuery(sql1);
    while (rs1.next()){
        String name1 = rs1.getString(2);
        String img1 = rs1.getString(6);
        String price1 = String.valueOf(rs1.getInt(3));

        out.println("<img src =" + img1 + " data-name = \"" + name1 +
                "\" data-price = " + price1 +
                " width = 100 height = 100 >");
    }
%>
</body>
</html>
