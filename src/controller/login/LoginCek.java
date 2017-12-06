package controller.login;

import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login/cek")
public class LoginCek extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel user = new UserModel("", "", "", "",  true,  null);
        user.setUserId(username);
        user.setPassword(password);

        LoginService loginservice = new LoginService();
        boolean check = loginservice.checkUser(user);
        if(check){

            if(user.getUserId().startsWith("ADM")) {

               response.sendRedirect("/admin/users");
            }
            else {
                response.sendRedirect("/cashier");
            }
        }else{
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
