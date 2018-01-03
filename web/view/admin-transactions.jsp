<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 28-Dec-17
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            var startDate = "<%=request.getAttribute("startDate")%>";
            var endDate = "<%=request.getAttribute("endDate")%>";

            var table = $('#transactions').DataTable({
                "ajax": {
                    "url": "/admin/transactions/list",
                    "dataSrc": "",
                    "data": function( d ) {
                        d.startDate = startDate;
                        d.endDate = endDate;
                    }
                },
                "columns": [
                    {"data": "transactionId", "className": "pl-4"},
                    {"data": "cashierId", "className": "pl-4"},
                    {"data": "timeTrans", "className": "pl-4", "orderable": false},
                    {"data": "currencyWrapper", "orderData": [4], "className": "text-right pr-5"},
                    {"data": "totalPrice", "className": "pl-4", "visible": false},
                    {
                        data: null,
                        className: "text-center",
                        defaultContent: '<a href="#" class="detail table-link">See Detail</a>'
                    }
                ]
            });
            $('#transactions th').removeClass('text-right');

            $('#transactions').on('click', 'a.detail', function (e) {
                e.preventDefault();
                var transactionId = table.row($(this).parents('tr')).data().transactionId;

                $.get('/admin/detail-transactions/list', {transactionId: transactionId})
                    .done(function (response) { // on success --TODO: alert if error occurs
                        if(response.length > 0) {
                            var modalContent = "";
                            for (var i = 0; i < response.length; i++) {
                                modalContent += "<br><h6>" + response[i].productId + " - " + response[i].productName + "</h6><hr>";
                                modalContent += "<p>" + response[i].currencyWrapper + " x " + response[i].quantity + " = " + response[i].currencyWrapper + "</p><br>";
                            }

                            $('#transactionIdSpan').text(response[0].transactionId);
                            $("#transactionModalBody").html(modalContent);
                            setTimeout(function () {
                                $('#detailModal').modal('show');
                            }, 1000);
                        } else {
                            var modalContent = "<h6>No transaction details available.</h6><br>";

                            $('#transactionIdSpan').text("No Records");
                            $("#transactionModalBody").html(modalContent);
                            setTimeout(function () {
                                $('#detailModal').modal('show');
                            }, 1000);
                        }
                    })
                    .fail(function () {
                        notyf.alert("Retrieve details failed.")
                    });
            });

        });
    </script>
    <style>
        #transactions {
            border-radius: 5px;
            border: 3px solid rgba(255,255,255,.8);
        }

        #transactions>thead>tr>th {
            border-bottom: 1px solid #000000;
        }

        .pagination li a {
            color: #000000;
        }

        .pagination>li.active>a {
            border: 1px solid #455a64  !important;
            background: #455a64  !important;
            color: #FFFFFF;
        }
    </style>
    <title>Transactions History</title>
</head>
<body>

<%@ include file="/view/session-admin.jsp" %>
<%@ include file="/view/header-admin.jsp" %>
<div class="container mt-5">
    <h1 class="text-center">Transactions</h1>
    <p class="text-white text-center mb-5">
        <%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
        <%
            String startStr = request.getParameter("startDate");
            String endStr = request.getParameter("endDate");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date startDate = formatter.parse(startStr);
            Date endDate = formatter.parse(endStr);
            SimpleDateFormat longDateFormatter = new SimpleDateFormat("MMM dd yyyy");
            String formattedStartDate = longDateFormatter.format(startDate);
            String formattedEndDate = longDateFormatter.format(endDate);
            out.println(formattedStartDate + " - " + formattedEndDate);
        %>
    </p>
    <table id="transactions" class="table-light table-striped table-hover table-sm" width="100%">
        <thead>
        <tr class="text-center">
            <th>Transaction ID</th>
            <th>Cashier ID</th>
            <th>Time Stamp</th>
            <th>Total Price</th>
            <th>Total Price Unwrapped</th>
            <th>Action</th>
        </tr>
        </thead>
    </table>

    <!-- Modal -->
    <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="detailModalLabel">Transaction Detail - <span id="transactionIdSpan"></span></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="transactionModalBody"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-dark" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
