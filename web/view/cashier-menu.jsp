<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28/11/2017
  Time: 11.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Date"%>

<html>
<head>
    <title>Cashier</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/cashierStyle.css">
    <script src="../assets/js/jquery-3.2.1.min.js"></script>

    <style>
        .hidden, .removed{
            display:none;
        }
        #cart-div{
            height: 65%;
        }
        td{
          color:#ffffff;
        }

        #total-price{
            color: #ffffff;
            font-size: large;
        }
    </style>

</head>
<body>
<header>
    <h3>POS SYSTEM</h3> <br>

    <div class="header">
        <label>Cashier 	: </label> <%= session.getAttribute("userId") %> <br>
        <label>Date 	:</label> <%=new Date() %> <br>
    </div>

    <div class="header">
        <div id="header2">
            <img id="logout" src="../assets/img/logout.png"> <br>
            <text id="logout-txt">logout</text>
        </div>
    </div>

    <hr>
</header>

<div class="menu">
    <nav>
        <a href="#snack" target="menu" class="nav-link">Snack</a>
        <a href="#appetizer" target="menu" class="nav-link">Appetizer</a>
        <a href="#main-dish" target="menu" class="nav-link">Main Dish</a>
        <a href="#dessert" target="menu" class="nav-link">Dessert</a>
        <a href="#drink" target="menu" class="nav-link">Drink</a>
    </nav>

    <div id="menu-container">
        <div id="img-container">
            <div id="main-dish" class="active">
                <%@include file='../view/img-dish.jsp'%>
            </div>
            <div id="dessert" class="hidden">
                <%@include file='../view/img-dessert.jsp'%>
            </div>
            <div id="snack" class="hidden">
                <%@include file="img-snack.jsp"%>
            </div>
            <div id="appetizer" class="hidden">
                <%@include file="img-appetizer.jsp"%>
            </div>
            <div id="drink" class="hidden">
                <%@include file="img-drink.jsp"%>
            </div>
        </div>

        <div id="add-container">
            <!--<form> -->
                <p>Quantity
                    <input type="number" name="quantity" id="item-qty">
                    <br>
                    <input type="submit" name="add-menu" id="btn-add" value="Add">
                </p>
           <!-- </form> -->
        </div>
    </div>
</div>

<div class="menu">
    <div id="cart-div">
        <table id="cart-body">
        </table>
    </div>
    <div id="total-price">

    </div>
     <br>

    <form>
        <p>
            <input type="reset" id="reset-btn" name="reset" class="btn-bayar" value="RESET">
            <input type="submit" class="btn-bayar" name="pay" value="PAY">
        </p>
    </form>

</div>

<script src="../assets/js/main.js"></script>

</body>
</html>
