<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 31/12/2017
  Time: 14.05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userId = (String) request.getSession().getAttribute("userId");

    if (userId == null) {
        response.sendRedirect("/login");
    } else {
        if(userId.startsWith("ADM")) {
            response.sendRedirect("/admin/products");
            return;
        }
        if(userId.startsWith("CSH")) {
            response.sendRedirect("/cashier");
            return;
        }
    }
%>
<html>
    <head>
        <title>$Title$</title>
     </head>
    <body>
    $END$
    </body>
</html>
