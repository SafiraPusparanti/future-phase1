package controller.cartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/reset")
public class CartReset extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("cart", null);
        request.getSession().setAttribute("totalPrice", "0");

    }
}
