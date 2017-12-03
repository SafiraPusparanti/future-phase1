<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 03-Dec-17
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
@import url("../assets/css/bootstrap.min.css");
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/bootstrap.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/header.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/layout.css" media="screen,projection"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.16/fc-3.2.3/r-2.2.0/datatables.min.css"/>

    <script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.16/r-2.2.0/datatables.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            var table = $('#ledger').DataTable({
                "ajax": {"url": "/admin/ledger/weekly", "dataSrc": ""},
                "columns": [
                    {"data": "ledgerDate"},
                    {"data": "income"}
                ]
            });

            $('#weekly-button').on('click', function () {
                table.ajax.url('/admin/ledger/weekly').load();
            });

            $('#monthly-button').on('click', function () {
                table.ajax.url('/admin/ledger/monthly').load();
            });

            $('#yearly-button').on('click', function () {
                table.ajax.url('/admin/ledger/yearly').load();
            });
        });
    </script>
    <title>View Ledger</title>
</head>
<body>
<%@ include file="/view/header.jsp" %>

<div class="row">
    <div class="col-1"><br>

        <button type="button" id="weekly-button" class="btn btn-secondary">weekly</button>
        <button type="button" id="monthly-button" class="btn btn-secondary">monthly</button>
        <button type="button" id="yearly-button" class="btn btn-secondary">yearly</button>

    </div>
    <div class="col-1">
        <div style="width: 50%; height: 100%; position:relative; overflow: hidden; border-right: solid 2px white"></div>
    </div>
    <div class="col"><br>
        <div class="container">
            <h1 class="text-center">Ledger</h1><br><br>

            <table id="ledger" class="table table-light table-bordered table-hover table-sm"
                   width="100%">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Income</th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>
</body>
</html>
