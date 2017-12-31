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
    <script src="../assets/js/jquery-3.2.1.min.js"></script>
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
    String sql = "SELECT * FROM product WHERE category_id LIKE 'MD'";

    ResultSet rs = state.executeQuery(sql);
    while (rs.next()){
        String name = rs.getString(2);
        String img = rs.getString(6);
        String price = String.valueOf(rs.getInt(3));
        out.println("<img src =" + img + " data-name = \"" + name +
                "\" data-price = " + price +
                " width = 100 height = 100 >");
        //out.println(price);
    }
%>
</body>
</html>
