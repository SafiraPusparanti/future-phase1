<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 25-Oct-17
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link type="text/css" rel="stylesheet" href="../assets/css/links.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/bootstrap.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/layout.css" media="screen,projection"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.16/fc-3.2.3/r-2.2.0/datatables.min.css"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/notyf.min.css">
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/jqc-1.12.3/dt-1.10.16/b-1.4.2/sl-1.2.3/datatables.min.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="../assets/Editor-1.6.5/css/editor.dataTables.css"/>--%>

    <script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../assets/js/notyf.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.16/r-2.2.0/datatables.min.js"></script>
    <%--<script type="text/javascript" src="https://cdn.datatables.net/v/dt/jqc-1.12.3/dt-1.10.16/b-1.4.2/sl-1.2.3/datatables.min.js"></script>--%>
    <%--<script type="text/javascript" src="../assets/Editor-1.6.5/js/dataTables.editor.js"></script>--%>

    <script type="text/javascript">
        $(document).ready(function () {
//            $('#success-delete').hide();
//            $('#fail-delete').hide();
//            $('#success-add').hide();
//            $('#fail-add').hide();

            var notyf = new Notyf({
                delay: 5000,
            })


            var table = $('#users').DataTable({
//            "processing": true,
//            "serverSide": true,
                "dom": '<"row"<"col-md-6"i><"col-md-6"f>><"row"<"col-md-12"t>><"row"<"col-md-12"p>>',
                "ajax": {"url": "/admin/users/list", "dataSrc": ""},
                "columns": [
                    {"data": "userId"},
                    {"data": "name"},
                    {"data": "email"},
                    {
                        data: null,
                        className: "text-center",
                        defaultContent: '<a href="" class="remove table-link">Delete</a>'
                    }
                ]
            });

            $('#user-form').submit(function (e) {
                e.preventDefault()

                var name = $("#name").val();
                var email = $("#email").val();
                var password = $("#password").val();
                var role = $("input[name=role]:checked").val();

                $.post('/admin/users/add', {name: name, email: email, password: password, role: role},
                    function () { // on success --TODO: alert if error occurs

                        $('#addedModal').modal('show');
                        $('#addedModalOk').on('click', function () {
                            notyf.confirm(name + ' have been added to the record!');
                        });

                        $("#success-add").fadeTo(2000, 500).slideUp(500, function () {
                            $("#success-add").slideUp(500);
                        });

                        $("#name").val("");
                        $("#email").val("");

                        setTimeout(function () {
                            table.ajax.reload();
                        }, 1000);
                    });

            });

            $('#users').on('click', 'a.remove', function (e) {
                e.preventDefault();
                var id = table.row($(this).parents('tr')).data().userId;
                $('#deleteId').val(id);
                $('#deleteModal').modal('show');
            });


            $('#submit-delete').on('click', function () {
                var userId = $("#deleteId").val();

                $.post('/admin/users/delete', {deleteId: userId},
                    function () { // on success --TODO: alert if error occurs

                        notyf.confirm(userId + ' have been deleted from the record.');

                        $("#success-delete").fadeTo(2000, 500).slideUp(500, function () {
                            $("#success-delete").slideUp(500);
                        });
                        setTimeout(function () {
                            table.ajax.reload();
                        }, 1000);
                    });

            });
        });
    </script>
    <title>Manage Users</title>
</head>
<style>
    #users {
        border-radius: 5px;
        border: 3px solid rgba(255,255,255,.8);
    }

    #users>thead>tr>th {
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

    .v-gray-line {
        width: 50%;
        height: 100%;
        position:relative;
        overflow: hidden;
        border-right: 1px solid rgba(255,255,255,.25)
    }
</style>
<body>

<%--<%@ include file="/view/session-admin.jsp" %>--%>
<%@ include file="/view/header-admin.jsp" %>

<div class="row">
    <div class="col-4 mt-5">
        <div style="margin-left: 5vw">
            <h1 class="text-center mb-5">Add User</h1>
            <form id="user-form">
                <div class="form-group">
                    <label for="name" class="text-white">Name*</label>
                    <input type="text" class="form-control bg-dark text-white" id="name" name="name" placeholder="Enter full name"
                           required/>
                </div>
                <div class="form-group">
                    <label for="email" class="text-white">Email*</label>
                    <input type="email" class="form-control bg-dark text-white" id="email" name="email" placeholder="Enter email"
                           required/>
                </div>
                <div class="form-group">
                    <label for="password" class="text-white">Password*</label>
                    <input type="password" class="form-control bg-dark text-white" id="password" name="password" pattern=".{6,16}"
                           placeholder="Enter password" required/>
                    <small id="passwordHelp" class="form-text text-secondary">Enter 6-16 characters.</small>
                </div>
                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-legend col-sm-2 text-white">Role*</legend>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <label class="form-check-label text-white">
                                    <input class="form-check-input" type="radio" name="role" id="adminRadio" value="T"
                                           checked/>
                                    Admin
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label text-white">
                                    <input class="form-check-input" type="radio" name="role" id="cashierRadio"
                                           value="F"/>
                                    Cashier
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
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
        <div class="v-gray-line"></div>
    </div>
    <div class="col mt-5">
        <div style="margin-right: 5vw">
            <h1 class="text-center mb-5">Users</h1>
            <table id="users" class="table-light table-striped table-hover table-sm" width="100%">
                <thead>
                <tr class="text-center">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                </thead>
            </table>

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
                            Are you sure you wish to delete this row?
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
                            User has been successfully added to the record.
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="addedModalOk" class="btn btn-outline-dark" data-dismiss="modal">Ok
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
