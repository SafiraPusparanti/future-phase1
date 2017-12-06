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
    <link rel="stylesheet" type="text/css" href="../assets/css/cashierStyle.css">
</head>
<body>
<header>
    <h3>POS SYSTEM</h3> <br>

    <div class="header">
        <label>Cashier 	: </label> <br>
        <label>Date 	:</label> <br>
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
        <a href="../view/img-snack.jsp" target="menu" class="nav-link">Snack</a>
        <a href="../view/img-appetizer.jsp" target="menu" class="nav-link">Appetizer</a>
        <a href="../view/img-dish.jsp" target="menu" class="nav-link">Main Dish</a>
        <a href="../view/img-dessert.jsp" target="menu" class="nav-link">Dessert</a>
        <a href="../view/img-drink.jsp" target="menu" class="nav-link">Drink</a>
    </nav>

    <div id="menu-container">
        <div id="img-container">
            <iframe src="../view/img-dish.jsp" width="450" height="400" name="menu"></iframe>


        </div>

        <div id="add-container">
            <form>
                <p>Quantity
                    <input type="number" name="quantity">
                    <br>
                    <input type="submit" name="add-menu" id="btn-add" value="Add">
                </p>
            </form>
        </div>
    </div>
</div>

<div class="menu">
    <iframe src="demo_iframe.htm" height="400" width="600">
    </iframe>
    <br>

    <form>
        <p>
            <input type="reset" name="reset" class="btn-bayar" value="RESET">
            <input type="submit" class="btn-bayar" name="pay" value="PAY">
        </p>
    </form>

</div>

</body>
</html>
