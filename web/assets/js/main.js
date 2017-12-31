// var name;
// var price;
// var qty;
// var total = 0;
// var tprice;
$(document).ready(function () {
    var notyf = new Notyf({
        delay: 5000,
    });
    var categoryId = "AP";

    var table = $('#products').DataTable({
        "scrollY": 400,
        "scrollX": true,
        "ordering": false,
        "paging": false,
        "info": false,
        "ajax": {
            "type": "GET",
            "url": "/admin/products/list",
            "dataSrc": "",
            "data": function (d) {
                d.categoryId = categoryId;
            }
        },
        "columns": [
            {"data": "productId"},
            {"data": "name"},
            {"data": "price"},
            {"data": "isAvailable"}
        ],
        "rowId": 'productId',
        "select": true,
        "dom": 'Bfrtip'
    });

    var cartTable = $('#shoppingCart').DataTable({
        "scrollY": 400,
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
            {"data": "subTotal"}
        ],
        "rowId": 'productId',
        "select": true,
        "dom": 'Bfrtip'
    });

    $('#products tbody').on( 'click', 'tr', function() {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );

    $(document).on('click', 'img', function () {
        name = $(this).attr("data-name");
        price = $(this).attr("data-price");
        $(this).css("border", "3px solid white");
        //$(this).attr("border-color", "white");


    });

    $("#btn-add").on('click', function (e) {
        e.preventDefault();
        // qty = $("#item-qty").val()
        // $("#cart-body").append(
        //     "<tr id='list-menu'>" +
        //     "<td width='250'>" + name + "</td>" +
        //     "<td width='100'>" + qty + "</td>" +
        //     "<td width='100' class='price'>" + qty * price + "</td>" +
        //     "<td width='100'> <input class='delete-btn' type='button' value='Delete'></td>" +
        //
        //     "</tr>"
        // );
        // tprice = qty * price;
        // total += tprice;
        // console.log(total);
        // document.getElementById("total-price").innerHTML = "Total : " + total;
        //
        // $("img").css("border", "none");

        //$("img").attr("height", "100");
        //console.log(qty);
        var productId = table.row('.selected').data().productId;
        var productName = table.row('.selected').data().name;
        var price = table.row('.selected').data().price;
        var quantity = $("#item-qty").val();
        $.post('/cart/add', {productId: productId, productName: productName, quantity: quantity, price: price},
            function () { // on success --TODO: alert if error occurs

                notyf.confirm(productId + ' have been added to the cart!');

                setTimeout(function () {
                    cartTable.ajax.reload();
                    $("#total-price-span").text(totalPrice);
                }, 1000);
            });
    });

    $("#btn-pay").on('click', function (e) {
        e.preventDefault();

        $.post('/cashier/pay',
            function () { // on success --TODO: alert if error occurs

                notyf.confirm('Payment succeed!');

                setTimeout(function () {
                    cartTable.ajax.reload();
                }, 1000);
            });
    });

    $(document).on('click', '.delete-btn', function () {
        $(this).parent().parent().remove();
        var min = parseInt(($(this).parent().parent().children(".price").html()));
        total = total - min;
        document.getElementById("total-price").innerHTML = "Total : " + total;

    });

    $("#reset-btn").on('click', function () {
        //console.log("tes");
        $("#cart-body").empty();
        total = 0;
        document.getElementById("total-price").innerHTML = "Total : " + total;
    });

    // $("a").click(function (e) {
    //     e.preventDefault();
    //     var href = $(this).attr("href").split('#');
    //
    //     $(".active").removeClass("active").addClass("hidden");
    //     $.each($.find(".hidden"), function (key, value) {
    //         if (value.id === href[1]) {
    //             value.className = 'active';
    //         }
    //     });
    // });

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
});
