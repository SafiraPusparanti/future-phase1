package controller.user;

import service.UserService;
import service.UserServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/users/delete")
public class UserDelete extends HttpServlet {
    UserService userService = new UserServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            userService.deleteUser(request.getParameter("deleteId"));
            response.setStatus(200);

        } catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
