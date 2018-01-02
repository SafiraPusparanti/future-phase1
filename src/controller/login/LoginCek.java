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
        String password = request.getParameter("password").hashCode() + "";
        UserModel user = new UserModel("", "", "", "",  true);
        user.setUserId(username);
        user.setPassword(password);

        HttpSession session = request.getSession();
        //System.out.println(user.getUserId());

        LoginService loginservice = new LoginService();
        boolean check = loginservice.checkUser(user);
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
        }else{
            session.setAttribute("loginMessage", "Wrong username and/or password.");
            response.sendRedirect("/login");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
