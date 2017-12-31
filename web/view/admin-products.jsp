<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 10-Dec-17
  Time: 1:51 AM
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
          href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/notyf.min.css">

    <%--<script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../assets/js/notyf.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/select/1.2.4/js/dataTables.select.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#show-detail").hide();
            var categoryId = "AP";

            var notyf = new Notyf({
                delay: 5000,
            })

            var table = $('#products').DataTable({
//                "processing": true,
//                "serverSide": true,
                "scrollY":300,
                "scrollX":true,
//                "scroller":false,
                "ordering": false,
                "paging": false,
                "searching": false,
//                "lengthChange": false,
                "info": false,
//                "deferRender": true,
                "ajax": {
                    "type": "GET",
                    "url": "/admin/products/list",
                    "dataSrc": "",
                    "data": function( d ) {
                        d.categoryId= categoryId;
                    }},
                "columns": [
                    {"data": "productId"},
                    {"data": "name"}
                ],
                "rowId": 'productId',
                "select": true,
                "dom": 'Bfrtip'
            });

            //AJAX OF PRODUCT DETAIL
            function ajaxDetail(id) {
                $.ajax({
                    type: "GET",
                    url: "/admin/products/detail",
                    dataSrc: "",
                    data: {"productId": id},
                    success: function (response) {
                        if(response.length > 0){
                            var status;
                            if(response[0].isAvailable) {
                                status = "Available";
                            } else {
                                status = "Unavailable";
                            }

                            var productImage = "";
                            if (response[0].imageUrl == null || response[0].imageUrl == "") {
                                productImage += '<div class="small-link"><br><br><br><a href="#" role="button" class="setImage">No image available.</a></div>';
                            } else {
                                productImage += '<img src="' + response[0].imageUrl + '" style="height:100px;"/><br>';
                                productImage += '<div class="small-link"><a href="#" role="button" class="setImage">change image</a></div>';
                            }
                            var productDetail = '<p>' + response[0].productId + '</p>';
                            productDetail += '<p>' + response[0].name + '</p>';
                            productDetail += '<p>' + response[0].price + '</p>';
                            productDetail += '<p>' + status + '</p>';
                            productDetail += '<button type="button" class="btn btn-outline-danger btn-block w-75 remove">Delete</button>';

                            $('#product-image').html(productImage);
                            $('#product-detail').html(productDetail);
                        }},
                    error: function (result) {
                        failure(result);
                    }
                });
            }

            $('#products tbody').on( 'click', 'tr', function() {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#show-detail').fadeOut();
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');

                    var id = table.row($(this)).data().productId;
                    ajaxDetail(id);

                    $('#show-detail').fadeIn();
                }
            } );

            //DELETE TRIGGER/MODAL
            $('#show-detail').on('click', 'button.remove', function () {
                $('#deleteSpan').text(table.row('.selected').data().productId);
                $('#deleteModal').modal('show');
            } );

            //SET IMAGE TRIGGER/MODAL
            $('#product-image').on('click', 'a.setImage', function () {
                $('#imageModal').modal('show');
            } );

            //IMAGE SUBMIT
            $('#submit-image').on('click', function () {
                var productId = table.row('.selected').data().productId;
                var image = $('#newImage').val();

                $.post('/admin/products/set-image', {productId: productId, imageUrl: image},
                    function () { // on success --TODO: alert if error occurs

                        notyf.confirm(productId + '\'s new image has been set.');

                        $("#newImagePId").val("");
                        $("#newImage").val("");

                        setTimeout(function () {
                            table.ajax.reload();
                        }, 1000);

                        setTimeout(function () {
                            table.row(':last').select();
                            var id = table.row(table.$('tr.selected')).data().productId;
                            ajaxDetail(id);
                        },2000);
                    });
            });

            //TOGGLE STATUS
            $('#show-detail').on('click', '#toggle-btn', function () {
                var productId = table.row('.selected').data().productId;
                var status = table.row(table.$('tr.selected')).data().isAvailable;

                $.post('/admin/products/toggle-status', {toggleStatusId: productId},
                    function () { // on success --TODO: alert if error occurs
                        notyf.confirm(productId + '\'s availability have been set ' + !status + '.');
                    });

                setTimeout(function () {
                    var id = table.row(table.$('tr.selected')).data().productId;
                    ajaxDetail(id);
                    table.ajax.reload();
                }, 1000);
            });

            //DELETE SUBMIT
            $('#submit-delete').on('click', function () {
                var productId = table.row('.selected').data().productId;

                $.post('/admin/products/delete', {deleteId: productId},
                    function () { // on success --TODO: alert if error occurs

                        notyf.confirm(productId + ' have been deleted from the record.');

                        setTimeout(function () {
                            $('#show-detail').fadeOut();
                            table.ajax.reload();
                        }, 1000);
                    });
            });

            $('#product-form').submit(function (e) {
                e.preventDefault()

                var name = $("#name").val();
                var price = $("#price").val();
                var image = $("#image").val();
                var category = $("#category").val();

                $.post('/admin/products/add', {name: name, price: price, imageUrl: image, categoryId: category},
                    function () { // on success --TODO: alert if error occurs

                        $('#addedModal').modal('show');
                        $('#addedModalOk').on('click', function () {
                            notyf.confirm(name + ' have been added to the record!');
                        });

                        $("#name").val("");
                        $("#price").val("");
                        $("#image").val("");
                        $("#category").val("");

                        setTimeout(function () {
                            categoryId = category;
                            $('.btn-group .active').button('toggle');
                            if (category == 'AP') {
                                $('#appetizer-btn').button('toggle');
                            } else if (category == 'MD') {
                                $('#main-btn').button('toggle');
                            } else if (category == 'SN') {
                                $('#snack-btn').button('toggle');
                            } else if (category == 'DR') {
                                $('#drink-btn').button('toggle');
                            } else {
                                $('#desert-btn').button('toggle');
                            }

                            table.ajax.reload();
                            $('#show-detail').fadeOut();
                        }, 1000);


                        setTimeout(function () {
                            table.row(':last').select();
                            var id = table.row(table.$('tr.selected')).data().productId;
                            ajaxDetail(id);
                            $('#show-detail').fadeIn();
                        },2000);
                    });
            });

            $('.btn-group').on('click', 'button', function() {
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
                $('#show-detail').fadeOut();
            });
        });
    </script>
    <style>
        .dataTables_wrapper {
            background-color: rgba(255,255,255,.8);
        }
    </style>
    <title>Manage Products</title>
</head>
<body>
<%@ include file="/view/admin-header.jsp" %>

<div class="row">
    <div class="col-4 mt-5">
        <div style="margin-left: 5vw">
            <h1 class="text-center mb-5">Add Product</h1>

            <form id="product-form" class="text-white">
                <div class="form-group">
                    <label for="name" class="text-white">Name*</label>
                    <input type="text" class="form-control bg-dark text-white" id="name" name="name" placeholder="Enter name"
                           required/>
                </div>
                <div class="form-group">
                    <label for="price" class="text-white">Price*</label>
                    <input type="number" min="5000" class="form-control bg-dark text-white" id="price" name="price" placeholder="Enter price"
                           required/>
                </div>
                <div class="form-group">
                    <label for="image" class="text-white">Image URL</label>
                    <input type="url"  pattern="https?://.+" class="form-control bg-dark text-white" id="image" name="image" placeholder="Enter image URL"/>
                </div>
                <div class="form-group">
                    <label for="category" class="text-white">Category*</label>
                    <select class="form-control bg-dark text-white" id="category" name="category" required>
                        <option value="" class="text-muted" selected disabled>Select category</option>
                        <option value="AP">Appetizer</option>
                        <option value="MD">Main Dish</option>
                        <option value="SN">Snack</option>
                        <option value="DR">Drink</option>
                        <option value="DE">Desert</option>
                    </select>
                </div><br>
                <div class="row">
                    <div class="col"></div>
                    <div class="col">
                        <button type="submit" id="submit-add" value="submit" class="btn btn-outline-light btn-block">
                            Add
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-1">
        <div style="width: 80%; height: 100%; position:relative; overflow: hidden; border-right: 1px solid rgba(255,255,255,.25)"></div>
    </div>
    <div class="col-3 ml-0 pl-0"><br>
        <div style="margin-left: 5vw">
            <div class="btn-toolbar justify-content-center mb-sm-1 mt-lg-5" role="toolbar">
                <div class="btn-group button-group-sm" role="group">
                    <button type="button" id="appetizer-btn" class="btn btn-outline-light active">Appetizer</button>
                    <button type="button" id="main-btn" class="btn btn-outline-light">Main Dish</button>
                </div>
            </div>
            <div class="btn-toolbar justify-content-center mb-lg-3" role="toolbar">
                <div class="btn-group button-group-sm" role="group">
                    <button type="button" id="snack-btn" class="btn btn-outline-light">Snack</button>
                    <button type="button" id="drink-btn" class="btn btn-outline-light">Drink</button>
                    <button type="button" id="desert-btn" class="btn btn-outline-light">Desert</button>
                </div>
            </div>
            <table id="products" class="table table-light table-hover table-sm" width="100%">
                <thead style='display:none;'>
                </thead>
            </table>
        </div>
    </div>
    <div class="col" id="show-detail"><br>
        <div style="margin-right: 5vw">
            <div class="row mt-lg-5 mb-lg-2" style="height:180px;">
                <div class="col-8 mt-lg-5" id="product-image">
                </div><br>
                <div class="col"></div>
            </div>
            <div class="row">
                <div class="col-5 text-white">
                    <p>Product ID:</p>
                    <p>Name:</p>
                    <p>Price:</p>
                    <p>Status:</p>
                    <button type="button" class="btn btn-outline-light btn-block" id="toggle-btn">Toggle Status</button>
                </div>
                <div class="col text-white text-truncate" id="product-detail">
                    <p>Detail goes here..</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Delete Record</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                Are you sure you wish to delete <span id="deleteSpan"></span>?
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deleteId" name="deleteId">
                <button type="button" class="btn btn-outline-dark" data-dismiss="modal">Close</button>
                <button type="submit" id="submit-delete" class="btn btn-outline-danger" value="submit"
                        data-dismiss="modal">Delete
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addedModal" tabindex="-1" role="dialog" aria-labelledby="addedModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addedModalLabel">Success!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                Product has been successfully added to the record.
            </div>
            <div class="modal-footer">
                <button type="button" id="addedModalOk" class="btn btn-outline-dark" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="imageModalLabel">Set Product Image</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Enter image URL below:</p>
                <input class="form-control" type="url"  pattern="https?://.+" id="newImage" name="newImage">
            </div>
            <div class="modal-footer">
                <input type="hidden" id="newImagePId" name="newImagePId">
                <button type="submit" id="submit-image" class="btn btn-outline-primary" value="submit"
                        data-dismiss="modal">Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
