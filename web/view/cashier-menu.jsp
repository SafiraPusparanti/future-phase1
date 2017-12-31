<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28/11/2017
  Time: 11.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cashier</title>
    <%--<link rel="stylesheet" type="text/css" href="../assets/css/cashierStyle.css">--%>
    <link type="text/css" rel="stylesheet" href="../assets/css/bootstrap.min.css" media="screen,projection"/>

    <link type="text/css" rel="stylesheet" href="../assets/css/layout.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/links.css" media="screen,projection"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/notyf.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../assets/js/notyf.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/select/1.2.4/js/dataTables.select.min.js"></script>

    <style>
        /*.hidden, .removed{*/
        /*display:none;*/
        /*}*/
        /*#cart-div {*/
            /*height: 65%;*/
        /*}*/

        /*td{*/
        /*color:#ffffff;*/
        /*}*/

        .dataTables_scroll {
            background-color: rgba(255, 255, 255, .8);
        }
        #total-price {
            color: #ffffff;
            font-size: large;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            var notyf = new Notyf({
                delay: 5000,
            });
            var categoryId = "AP";
            setTimeout(function () {
                cartTotalPrice();
            }, 1500);

            var table = $('#products').DataTable({
                "scrollY": 400,
                "scrollX": true,
                "ordering": false,
                "paging": false,
                "info": false,
                "ajax": {
                    "type": "GET",
                    "url": "/cashier/products/list",
                    "dataSrc": "",
                    "data": function (d) {
                        d.categoryId = categoryId;
                    }
                },
                "columns": [
                    {"data": "productId"},
                    {"data": "name"},
                    {"data": "price"},
                    {"data": "imageUrl"}
                ],
                "columnDefs" : [{
                    "targets" : 3,
                    "data": "imageUrl",
                    "render" : function (data, type, row) {
                        return '<img height="100px" width="100px" src="'+data+'"/>';
                    }
                }],
                "rowId": 'productId',
                "select": true,
                "dom": 'Bfrtip'
            });

            var cartTable = $('#shopping-cart').DataTable({
                "scrollY": 420,
                "scrollX": true,
                "ordering": false,
                "paging": false,
                "info": false,
                "searching": false,
                "ajax": {
                    "type": "GET",
                    "url": "/cart/list",
                    "dataSrc": ""
                },
                "columns": [
                    {"data": "productId"},
                    {"data": "productName"},
                    {"data": "quantity"},
                    {"data": "subTotal"},
                    {
                        data: null,
                        className: "text-center",
                        defaultContent: '<a href="#" class="remove table-link">Delete</a>'
                    }
                ],
                "rowId": 'productId',
                "dom": 'Bfrtip'
            });

            $('#products tbody').on('click', 'tr', function () {
                if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });

            $("#btn-add").on('click', function (e) {
                e.preventDefault();
                var productId = table.row('.selected').data().productId;
                var productName = table.row('.selected').data().name;
                var price = table.row('.selected').data().price;
                var quantity = $("#item-qty").val();
                $.post('/cart/add', {productId: productId, productName: productName, quantity: quantity, price: price},
                    function () { // on success --TODO: alert if error occurs
                        notyf.confirm(productId + ' have been added to the cart!');

                        setTimeout(function () {
                            cartTable.ajax.reload();
                            cartTotalPrice();
                        }, 1000);
                    });
            });

            function cartTotalPrice() {
                $.ajax({
                    type: "GET",
                    url: "/cart/total-price",
                    dataSrc: "",
                    success: function (response) {
                        if (response.length > 0) {
                            $(".total-price-span").text(response[0]);
                        }
                    }
                });
            }

            $("#payment-confirm").on('click', function (e) {
                e.preventDefault();

                $.post('/cashier/pay',
                    function () { // on success --TODO: alert if error occurs

                        notyf.confirm('Payment succeed!');

                        setTimeout(function () {
                            cartTable.ajax.reload();
                        }, 1000);

                        setTimeout(function () {
                            cartTotalPrice();
                        }, 2000);
                    });
            });

            $("#reset-btn").on('click', function () {
                $.post('/cart/reset',
                    function () { // on success --TODO: alert if error occurs

                        notyf.confirm('Cart has been successfully cleared!');

                        setTimeout(function () {
                            cartTable.ajax.reload();
                        }, 1000);

                        setTimeout(function () {
                            cartTotalPrice();
                        }, 2000);
                    });
            });

            $('.btn-group').on('click', 'button', function () {
                $('.btn-group .active').button('toggle');
                $(this).button('toggle');

                if (this.id == 'appetizer-btn') {
                    categoryId = 'AP';
                } else if (this.id == 'main-btn') {
                    categoryId = 'MD';
                } else if (this.id == 'snack-btn') {
                    categoryId = 'SN';
                } else if (this.id == 'drink-btn') {
                    categoryId = 'DR';
                } else {
                    categoryId = 'DE';
                }
                table.ajax.reload();
            });

            $('#shopping-cart').on('click', '.remove', function (e) {
                e.preventDefault();
                var productId = table.row($(this).parents('tr')).data().productId;
                $.post('/cart/delete', {productId: productId},
                    function () { // on success --TODO: alert if error occurs

                        notyf.confirm(productId + ' have been deleted from the cart.');

                        setTimeout(function () {
                            cartTable.ajax.reload();
                            cartTotalPrice();
                        }, 1000);
                    });

            });
        });
    </script>
</head>
<body>
<%@ include file="/view/header.jsp" %>

<div class="row">
    <%--<div class="col-2 ledger-type main-link mr-0 pr-0 text-center"><br>--%>
    <%--<a id="appetizer-btn">Appetizer</a><br><br><br>--%>
    <%--<a id="main-btn">Main Dish</a><br><br><br>--%>
    <%--<a id="snack-btn">Snack</a><br><br><br>--%>
    <%--<a id="drink-btn">Drink</a><br><br><br>--%>
    <%--<a id="desert-button">Desert</a><br><br><br>--%>
    <%--</div>--%>
    <%--<div class="col-1 w-5 ml-0 pl-0">--%>
    <%--<div class="v-white-line"></div>--%>
    <%--</div>--%>
    <div class="col-6 mt-5 w-100">



            <div class="btn-toolbar mb-3 ml-5 mr-5 justify-content-center" role="toolbar">
                <div class="btn-group button-group-sm" role="group">
                    <button type="button" id="appetizer-btn" class="btn btn-outline-light active">Appetizer</button>
                    <button type="button" id="main-btn" class="btn btn-outline-light">Main Dish</button>
                    <button type="button" id="snack-btn" class="btn btn-outline-light">Snack</button>
                    <button type="button" id="drink-btn" class="btn btn-outline-light">Drink</button>
                    <button type="button" id="desert-btn" class="btn btn-outline-light">Desert</button>
                </div>
            </div>


        <div id="menu-container" class="ml-5 mr-5">
            <table id="products" class="table table-light table-hover table-sm" width="100%">
                <thead style='display:none;'>
                </thead>
            </table>

            <div id="add-container" class="mt-3">
                <form>
                <p class="text-white">Quantity:
                    <div class="row">
                    <div class="col">
                    <input type="number" name="quantity" id="item-qty" class="form-control">
                    </div>
                    <div class="col">
                    <button class="btn btn-outline-light btn-block" type="submit" name="add-menu" id="btn-add" value="submit">Add</button>
                    </div>
                    </div>
                    </p>
                </form>
            </div>
        </div>
    </div>

    <div class="col-6 mt-5">
        <div class="menu ml-5 mr-5">
            <h2 class="text-white mb-3">Cart</h2>
            <div id="cart-div">
                <%--<table id="cart-body">--%>
                <%--</table>--%>
                <table id="shopping-cart" class="table table-light table-hover table-lg" width="100%">
                    <thead style='display:none;'>
                    </thead>
                </table>
            </div>
            <div id="total-price" class="text-white ml-5 mt-3">
                <p>Total Price:   <span class="total-price-span"></span></p>
            </div>


                <div class="row">
                    <div class="col">
                    <button id="reset-btn" class="btn btn-outline-light btn-block">RESET</button>
                    </div>
                    <div class="col">
                    <button id="btn-pay" class="btn btn-outline-light btn-block" data-toggle="modal" data-target="#payModal">PAY</button>
                    </div>
                </div>


        </div>
    </div>
</div>

<%--MODAL--%>
<div class="modal fade" id="payModal" tabindex="-1" role="dialog" aria-labelledby="payModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="payModalLabel">Success!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <p>Total price:</p>
                <p><span class="total-price-span"></span></p>
                <p>Amount of money:</p>
                <input class="form-control" type="number">
                <p>Change:</p>
                <p><span id="change-span"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" id="payment-confirm" class="btn btn-outline-dark" data-dismiss="modal">Confirm</button>
            </div>
        </div>
    </div>
</div>

<%--<script src="../assets/js/main.js"></script>--%>

</body>
</html>
