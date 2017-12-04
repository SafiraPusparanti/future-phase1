<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 08-Oct-17
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Date"%>
<nav>
    <h3 href="#" class="text-center text-white">POS SYSTEM</h3><br>
    <div class="row">
        <div class="col">
            <a class="text-white">Role: username</a><br>
            <%--<a class="text-white">Date: <%=new Date()%></a>--%>

            <a href="https://time.is/Jakarta" id="time_is_link" rel="nofollow" style="color:#ffffff"></a>
            <span id="Jakarta_z41c" style="color:#ffffff"></span>
            <script src="//widget.time.is/en.js"></script>
            <script>
                time_is_widget.init({Jakarta_z41c:{template:"DATE<br>TIME", date_format:"dayname, monthname dnum, year"}});
            </script>
        </div>
        <div class="col">
            <a href="#">
                <img class="float-right" border="0" alt="logout" src="../assets/img/logout.png" width="50" height="50">
            </a>
        </div>
    </div>
</nav>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
