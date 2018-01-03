package controller.login;

import model.UserModel;
import service.UserService;
import service.UserServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login/cek")
public class LoginCek extends HttpServlet {
    UserService  userService = new UserServiceDatabase();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password").hashCode() + "";
        Boolean check = false;
        UserModel user = new UserModel(username, "", "", password,  false);
        HttpSession session = request.getSession();

        try {
            check = userService.checkUser(user);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }

        if(check){
            session.setAttribute("userId", user.getUserId());

            if(user.getUserId().startsWith("ADM")) {
                session.removeAttribute("loginMessage");
                response.sendRedirect("/admin/products");
            }
            else {
                session.removeAttribute("loginMessage");
                response.sendRedirect("/cashier");
            }
            return;
        } else{
            session.setAttribute("loginMessage", "Wrong username and/or password.");
            response.sendRedirect("/login");
            return;
        }
    }
}
