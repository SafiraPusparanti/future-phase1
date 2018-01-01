package controller.login;

import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login/cek")
public class LoginCek extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel user = new UserModel("", "", "", "",  true);
        user.setUserId(username);
        user.setPassword(password);

        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("user", user);
        //System.out.println(user.getUserId());

        LoginService loginservice = new LoginService();
        boolean check = loginservice.checkUser(user);
        if(check){


            if(user.getUserId().startsWith("ADM")) {

               response.sendRedirect("/view/admin-menu.jsp");
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
