<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 03-Dec-17
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/bootstrap.min.css" media="screen,projection"/>
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
            $('#weekly-button').addClass("active");

            var table = $('#ledger').DataTable({
                "order": [],
                "ajax": {"url": "/admin/ledger/weekly", "dataSrc": ""},
                "columns": [
                    {"data": "ledgerDate", "orderable": false},
                    {"data": "income"}
                ]
            });

            $('#weekly-button').on('click', function () {
                $('.ledger-type a').removeClass('active');
                $(this).toggleClass('active');
                table.ajax.url('/admin/ledger/weekly').load();
            });

            $('#monthly-button').on('click', function () {
                $('.ledger-type a').removeClass('active');
                $(this).toggleClass('active');
                table.ajax.url('/admin/ledger/monthly').load();
            });

            $('#yearly-button').on('click', function () {
                $('.ledger-type a').removeClass('active');
                $(this).toggleClass('active');
                table.ajax.url('/admin/ledger/yearly').load();
            });
        });
    </script>
    <style>
        #ledger {
            border-radius: 5px;
            border: 3px solid rgba(255,255,255,.8);
        }

        #ledger>thead>tr>th {
            border-bottom: 1px solid #000000;
        }

        #ledger>tbody>tr>td:nth-child(1), #ledger>tbody>tr>th:nth-child(1), #ledger>thead>tr>td:nth-child(1), #ledger>thead>tr>th:nth-child(1) {
            padding-left: 10%;
        }

        .pagination li a {
            color: #000000;
        }

        .pagination>li.active>a {
            border: 1px solid #455a64  !important;
            background: #455a64  !important;
            color: #FFFFFF;
        }

        .v-white-line {
            width: 0%;
            height: 100%;
            position:relative;
            overflow: hidden;
            border-left: 1px solid rgba(255,255,255,.8)
        }

        .ledger-type {
            margin-top: 170px;
            font-size: 1.3em;
        }
    </style>
    <title>View Ledger</title>
</head>
<body>
<%@ include file="/view/admin-header.jsp" %>
<div class="row">
    <div class="col-2 ledger-type main-link mr-0 pr-0 text-center"><br>
        <a id="weekly-button">weekly</a><br><br><br>
        <a id="monthly-button">monthly</a><br><br><br>
        <a id="yearly-button">yearly</a><br><br><br>
    </div>
    <div class="col-1 w-5 ml-0 pl-0">
        <div class="v-white-line"></div>
    </div>
    <div class="col"><br>
        <div class="mr-5 pr-5 w-75">
            <h1 class="text-center">Ledger</h1><br><br>

            <table id="ledger" class="table table-light table-hover table-sm w-100">
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
