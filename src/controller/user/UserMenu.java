package controller.user;

import service.UserService;
import service.UserServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/users")
public class UserMenu extends HttpServlet {
    UserService userService = new UserServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/manage-users.jsp";

        //TODO: check whether user have already logged in as admin
        request.getRequestDispatcher(address).forward(request, response);
    }
}
