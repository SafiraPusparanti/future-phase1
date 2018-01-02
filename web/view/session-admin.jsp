<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 02-Jan-18
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String userId = (String) request.getSession().getAttribute("userId");

    if (userId == null) {
        response.sendRedirect("/login");
    } else {
        if(userId.startsWith("CSH")) {
            request.setAttribute("requestedPage", request.getRequestURI());
            response.sendRedirect("/403");
            return;
        }
    }
%>