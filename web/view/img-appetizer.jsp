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
    Connection con4 = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/POS",
            "postgres",
            "aldoleonardo");

    Statement state4 = con4.createStatement();
    String sql4 = "SELECT * FROM product WHERE category_id LIKE 'AP'";

    ResultSet rs4 = state4.executeQuery(sql4);
    while (rs4.next()){
        String name4 = rs4.getString(2);
        String img4 = rs4.getString(6);
        String price4 = String.valueOf(rs4.getInt(3));

        out.println("<img src =" + img4 + " data-name = \"" + name4 +
                "\" data-price = " + price4 +
                " width = 100 height = 100 >");
    }
%>
</body>
</html>
