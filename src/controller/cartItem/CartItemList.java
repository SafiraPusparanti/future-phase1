package controller.cartItem;

import com.google.gson.Gson;
import model.CartItemModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart/list")
public class CartItemList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, CartItemModel> shoppingCart = (HashMap<String, CartItemModel>) request.getSession().getAttribute("cart");

        if (shoppingCart == null) {
            shoppingCart = new HashMap<String, CartItemModel>();
            request.getSession().setAttribute("cart", shoppingCart);
        }

        List<CartItemModel> cartItemList = new ArrayList<CartItemModel>(shoppingCart.values());
        try {
            response.getWriter().write(new Gson().toJson(cartItemList));
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
