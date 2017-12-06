<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 06/12/2017
  Time: 10.53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement,
                java.sql.ResultSet, java.io.File" %>

<%
    boolean salah = false;

    if (!salah){

        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/POS",
                "postgres",
                "aldoleonardo"
        );

        Statement state=c.createStatement();

        String sql = "SELECT image_url FROM product WHERE category_id LIKE 'DR'";

        ResultSet rs=state.executeQuery(sql);

        while(rs.next()) {
            String foto = rs.getString("image_url");

            out.println("<img src=" + foto + " width = 100 height = 100>");
        }
    }
%>

</body>
</html>
