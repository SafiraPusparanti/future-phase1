package controller.user;

import com.google.gson.Gson;
import model.UserModel;
import service.UserService;
import service.UserServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/users/list")
public class UserList extends HttpServlet {
    UserService userService = new UserServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            List<UserModel> users = userService.getAllUsers();
            response.getWriter().write(new Gson().toJson(users));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
