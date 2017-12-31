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

@WebServlet("/cart/delete")
public class CartItemDelete extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, CartItemModel> shoppingCart = (HashMap<String, CartItemModel>) request.getSession().getAttribute("cart");

        shoppingCart.remove(request.getParameter("productId"));
        response.setStatus(200);
    }
}
