package controller.cartItem;

import model.CartItemModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cart/add")
public class CartItemAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, CartItemModel> shoppingCart = (HashMap<String, CartItemModel>) request.getSession().getAttribute("cart");
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String productName = request.getParameter("productName");
        float price = Float.parseFloat(request.getParameter("price"));

        if (shoppingCart.get(productId) != null) {
            int oldQuantity = shoppingCart.get(productId).getQuantity();
            int newQuantity = oldQuantity + quantity;
            float subTotal = newQuantity * price;

            CartItemModel newItem = new CartItemModel(productId, productName, price, newQuantity, subTotal);
            shoppingCart.put(productId, newItem);
        } else {
            float subTotal = quantity * price;
            CartItemModel newItem = new CartItemModel(productId, productName, price, quantity, subTotal);
            shoppingCart.put(productId, newItem);
        }
    }
}
