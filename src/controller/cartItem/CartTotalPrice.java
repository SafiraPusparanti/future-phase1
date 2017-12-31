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

@WebServlet("/cart/total-price")
public class CartTotalPrice extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, CartItemModel> shoppingCart = (HashMap<String, CartItemModel>) request.getSession().getAttribute("cart");

        float totalPrice = 0;
        for (String key : shoppingCart.keySet()) {
            totalPrice += shoppingCart.get(key).getSubTotal();
        }
        request.getSession().setAttribute("totalPrice", totalPrice);

        List<String> totalPriceList = new ArrayList<String>();

        String currencyWrapper = "";
        currencyWrapper = String.format("Rp %,.2f", totalPrice);
        totalPriceList.add(currencyWrapper);

        response.getWriter().write(new Gson().toJson(totalPriceList));
    }
}
