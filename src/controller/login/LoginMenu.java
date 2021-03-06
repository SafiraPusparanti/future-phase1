package controller.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginMenu extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId != null) {
            if(userId.startsWith("CSH")) {
                response.sendRedirect("/cashier");
            } else {
                response.sendRedirect("/admin/products");
            }
            return;
        }

        String address = "/view/login.jsp";

        request.getRequestDispatcher(address).forward(request, response);
    }
}
