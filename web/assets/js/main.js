var name;
var price;
var qty;
var total = 0;
var tprice;


$(document).on('click', 'img', function () {
            name = $(this).attr("data-name");
            price = $(this).attr("data-price");
                $(this).css("border", "3px solid white");
                //$(this).attr("border-color", "white");

});


$("#btn-add").on('click', function () {
    qty = $("#item-qty").val()
    $("#cart-body").append(
        "<tr id='list-menu'>" +
        "<td width='250'>" + name + "</td>" +
        "<td width='100'>" + qty + "</td>" +
        "<td width='100' class='price'>" + qty * price + "</td>" +
        "<td width='100'> <input class='delete-btn' type='button' value='Delete'></td>" +

        "</tr>"
    );
    tprice = qty * price;
    total += tprice;
    console.log(total);
    document.getElementById("total-price").innerHTML = "Total : " + total;

    $("img").css("border", "none");
    //$("img").attr("height", "100");
    //console.log(qty);
});


$(document).on('click','.delete-btn', function () {
   $(this).parent().parent().remove();
   var min = parseInt(($(this).parent().parent().children(".price").html()));
   total = total -  min;
    document.getElementById("total-price").innerHTML = "Total : " + total;

});

$("#reset-btn").on('click', function () {
   //console.log("tes");
    $("#cart-body").empty();
    total = 0;
    document.getElementById("total-price").innerHTML = "Total : " + total;
});

$("a").click(function (e) {
    e.preventDefault();
    var href= $(this).attr("href").split('#');

   $(".active").removeClass("active").addClass("hidden");
   $.each($.find(".hidden"), function (key, value) {
      if(value.id===href[1]){
          value.className='active';
      }
   });
});

