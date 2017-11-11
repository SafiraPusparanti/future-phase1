<%--
  Created by IntelliJ IDEA.
  User: Safira
  Date: 25-Oct-17
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
@import url("../assets/css/bootstrap.min.css");
<html>
<head>
    <%--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/bootstrap.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/header.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../assets/css/layout.css" media="screen,projection"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.16/fc-3.2.3/r-2.2.0/datatables.min.css"/>
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/jqc-1.12.3/dt-1.10.16/b-1.4.2/sl-1.2.3/datatables.min.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="../assets/Editor-1.6.5/css/editor.dataTables.css"/>--%>

    <script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.16/r-2.2.0/datatables.min.js"></script>
    <%--<script type="text/javascript" src="https://cdn.datatables.net/v/dt/jqc-1.12.3/dt-1.10.16/b-1.4.2/sl-1.2.3/datatables.min.js"></script>--%>
    <%--<script type="text/javascript" src="../assets/Editor-1.6.5/js/dataTables.editor.js"></script>--%>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#success-delete').hide();
            $('#fail-delete').hide();
            $('#success-add').hide();
            $('#fail-add').hide();

            var table = $('#kanmakan').DataTable({
//            "processing": true,
//            "serverSide": true,
                "ajax": {"url": "/admin/users/list", "dataSrc": ""},
                "columns": [
                    {"data": "userId"},
                    {"data": "name"},
                    {"data": "email"},
                    {
                        data: null,
                        className: "center",
                        defaultContent: '<a href="" class="remove">Delete</a>'
                    }
                ]
            });

            $('#submit-add').on('click', function () {
                var name = $("#name").val();
                var email = $("#email").val();
                var role = $("input[name=role]:checked").val();

                $.post('/admin/users/add', {name: name, email: email, role:role},
                    function () { // on success --TODO: alert if error occurs
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

            $('#kanmakan').on('click', 'a.remove', function (e) {
                e.preventDefault();
                var id = table.row($(this).parents('tr')).data().userId;
                $('#deleteId').val(id);
                $('#deleteModal').modal('show');
            });


            $('#submit-delete').on('click', function () {
                var userId = $("#deleteId").val();

                $.post('/admin/users/delete', {deleteId: userId},
                    function () { // on success --TODO: alert if error occurs
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
<body>
<%@ include file="/view/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col"><br>
            <h1 class="text-center">Add User</h1><br><br><br><br>
            <div class="alert alert-success" id="success-add">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <strong>Success! </strong>
                User have been added to the record.
            </div>

            <div class="alert alert-danger" id="fail-add">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <strong>Error. </strong>
                Fail to add user to the record.
            </div>
                <div class="form-group">
                    <label for="name" class="text-white">Name</label>
                    <input type="text" class="form-control bg-dark" id="name" name="name" placeholder="Enter full name">
                </div>
                <div class="form-group">
                    <label for="email" class="text-white">Email</label>
                    <input type="email" class="form-control bg-dark" id="email" name="email" placeholder="Enter email">
                </div>
                <fieldset class="form-group">
                    <div class="row">
                        <legend class="col-form-legend col-sm-2 text-white">Role</legend>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <label class="form-check-label text-white">
                                    <input class="form-check-input" type="radio" name="role" id="adminRadio" value="T"
                                           checked>
                                    Admin
                                </label>
                            </div>
                            <div class="form-check">
                                <label class="form-check-label text-white">
                                    <input class="form-check-input" type="radio" name="role" id="cashierRadio"
                                           value="F">
                                    Cashier
                                </label>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <button type="submit" id="submit-add" value="submit" class="btn btn-secondary">Add</button>

        </div>
        <div class="col-1">
            <div style="width: 50%; height: 100%; position:relative; overflow: hidden; border-right: solid 2px white"></div>
        </div>
        <div class="col"><br>
            <h1 class="text-center">Users</h1><br><br>

            <div class="alert alert-success" id="success-delete">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <strong>Success! </strong>
                User have been deleted from the record.
            </div>

            <div class="alert alert-danger" id="fail-delete">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <strong>Error. </strong>
                Fail to delete user from the record.
            </div>

            <table id="kanmakan" class="table table-light table-bordered table-hover table-sm" width="100%">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                </thead>
                <%--<tbody>--%>
                <%--<c:forEach items="${users}" var="users">--%>
                <%--<tr>--%>
                <%--<td><c:out value="${users.userId}"></c:out></td>--%>
                <%--<td><c:out value="${users.name}"></c:out></td>--%>
                <%--<td><c:out value="${users.email}"></c:out></td>--%>
                <%--<td>Delete</td>--%>
                <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            </table>
            <%--<form>--%>
            <%--<div class="form-group">--%>
            <%--<input type="text" class="form-control bg-dark" placeholder="Enter ID">--%>
            <%--</div>--%>
            <%--<button type="submit" class="btn btn-secondary">Search</button>--%>
            <%--</form>--%>
            <%--<a class="text-white">Name:</a><br>--%>
            <%--<a class="text-white">ID:</a><br>--%>
            <%--<a class="text-white">Email:</a>--%>

            <!-- Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Edit Record</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="modal-body">
                            Are you sure you wish to delete this row?
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="deleteId" name="deleteId">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" id="submit-delete" class="btn btn-danger" value="submit"
                                    data-dismiss="modal">Delete
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
