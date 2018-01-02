<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 08-Oct-17
  Time: 11:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<link type="text/css" rel="stylesheet" href="../assets/css/links.css" media="screen,projection"/>
<style>
    nav {
        width: 100%;
        border-bottom: 1px solid rgba(255, 255, 255, .8);
    }
</style>
<nav>
    <div class="row">
        <div class="col-1"></div>
        <div class="col">
            <table style="table-layout: fixed; width: 100%;">
                <tbody class="text-white" style=" position: relative;">
                <tr>
                    <td class="align-bottom" id="userId">Cashier: <%= session.getAttribute("userId") %></td>
                    <td class="text-center align-bottom lead">KANMAKAN</td>
                    <td class="text-right align-bottom" id="date">
                        <%
                            Date dNow = new Date();
                            SimpleDateFormat ft =
                                    new SimpleDateFormat("E dd MMM, yyyy");
                            out.print("<span class=\"text-white\">" + ft.format(dNow) + "</span>");
                        %>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-1"></div>
    </div>
    <div class="row mb-lg-2">
        <div class="col-1"></div>
        <div class="col">
            <a href="https://time.is/Jakarta" id="time_is_link" rel="nofollow" style="color:#ffffff"></a>
            <span id="Jakarta_z41c" style="color:#ffffff"></span>
            <script src="//widget.time.is/en.js"></script>
            <script>
                time_is_widget.init({Jakarta_z41c: {template: "TIME"}});
            </script>
        </div>
        <div class="col-3 main-link d-flex justify-content-around text-white">
            <p class="mb-0 pb-0">Point   of   Sale   System</p>
        </div>
        <div class="col logout-link text-right">
            <a href="/logout">Logout</a>
        </div>
        <div class="col-1"></div>
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
